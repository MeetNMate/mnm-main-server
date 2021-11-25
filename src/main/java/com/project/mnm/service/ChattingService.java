package com.project.mnm.service;

import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.ChattingRoom;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserChatting;
import com.project.mnm.dto.ChattingResponseDto;
import com.project.mnm.dto.ChattingRoomInsertDto;
import com.project.mnm.dto.ChattingRoomLatestResponseDto;
import com.project.mnm.repository.ChattingRepository;
import com.project.mnm.repository.ChattingRoomRepository;
import com.project.mnm.repository.UserChattingRepository;
import com.project.mnm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChattingService {
    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserChattingRepository userChattingRepository;

    public Chatting chattingHandler(Long cid, Chatting chatting) {
        User user = userRepository.findById(chatting.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        userChattingRepository.findByUserAndChattingRoom(user, chattingRoom)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 참여하고 있지 않습니다."));

        Chatting chat = new Chatting();
        chat.setUser(user);
        chat.setChattingRoom(chattingRoom);
        chat.setMessage(chatting.getMessage());
        chat.setIsRequest(chatting.getIsRequest());

        return chattingRepository.save(chat);
    }

    public Chatting makeChattingRoom(ChattingRoomInsertDto chattingRoomInsertDto) {
        User sender = userRepository.findById(chattingRoomInsertDto.getSenderUid())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        User receiver = userRepository.findById(chattingRoomInsertDto.getReceiverUid())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setRequestSuccess(false);
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        Timestamp now = new Timestamp(System.currentTimeMillis());

        userChattingRepository.save(UserChatting.builder()
                .user(sender)
                .chattingRoom(chattingRoom)
                .lastAccessAt(now)
                .build());

        userChattingRepository.save(UserChatting.builder()
                .user(receiver)
                .chattingRoom(chattingRoom)
                // 받는 사람은 lastAccessAt 일단 null
                .build());

        return chattingRepository.save(Chatting.builder()
                .user(sender)
                .chattingRoom(chattingRoom)
                .message("안녕하세요:D")
                .sendAt(now)
                .isRequest(false)
                .build());
    }

    public Boolean isExisted(Long sid, Long rid) {
        User sender = userRepository.findById(sid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        User receiver = userRepository.findById(rid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        List<UserChatting> userChattings = userChattingRepository.findByUser(sender);

        for (UserChatting userChatting : userChattings) {
             for(UserChatting uc : userChattingRepository.findByChattingRoom(userChatting.getChattingRoom())) {
                 if (uc.getUser() == receiver) return true;
             }
        }
        return false;
    }

    public List<ChattingRoom> getChattingRoomList(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        List<UserChatting> userChattings = userChattingRepository.findByUser(user);

        // lastAccessAt으로 정렬 -> JPA method로 변경하기
        userChattings.sort(new Comparator<UserChatting>() {
            @Override
            public int compare(UserChatting o1, UserChatting o2) {
                Timestamp t1 = o1.getLastAccessAt();
                Timestamp t2 = o2.getLastAccessAt();
                if (t1 == t2) return 0;
                else if (t1.before(t1)) return 1;
                else return -1;
            }
        });

        List<ChattingRoom> chattingRooms = new ArrayList<ChattingRoom>();

        for (UserChatting userChatting : userChattings) {
            chattingRooms.add(chattingRoomRepository.findById(userChatting.getChattingRoom().getId())
                    .orElseThrow(() -> new IllegalArgumentException("채팅 방을 찾을 수 없습니다.")));
        }

        return chattingRooms;
    }

    public ChattingRoomLatestResponseDto getChattingRoomLatest(String email, Long cid) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.getById(cid);

        UserChatting userChatting = userChattingRepository.findByUserAndChattingRoom(user, chattingRoom)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 들어있지 않습니다."));

        ChattingRoomLatestResponseDto chattingRoomLatestResponseDto = new ChattingRoomLatestResponseDto();
        Chatting latestChatting = chattingRepository.findTopByChattingRoomOrderBySendAtDesc(chattingRoom);
        List<UserChatting> userChattings = userChattingRepository.findByChattingRoom(chattingRoom);
        for (UserChatting uc : userChattings) {
            if (uc.getUser() != user) chattingRoomLatestResponseDto.setUid(uc.getUser().getId());
        }
        chattingRoomLatestResponseDto.setSendAt(latestChatting.getSendAt());
        chattingRoomLatestResponseDto.setMessage(latestChatting.getMessage());
        // greater than 이 정상 실행이 안돼서 일단 구현만 함
        // chattingRoomLatestDto.setNumber(chattingRepository.findByChattingRoomAndSendAtGreaterThan(chattingRoom, userChatting.getLastAccessAt()).size());
        System.out.println("-------------------1");
        if (userChatting.getLastAccessAt() == null) {
            chattingRoomLatestResponseDto.setNumber(chattingRepository.findByChattingRoom(chattingRoom).size());
        }
        else {
            chattingRoomLatestResponseDto.setNumber(chattingRepository.findByChattingRoom(chattingRoom).size() - chattingRepository.findByChattingRoomAndSendAtLessThan(chattingRoom, userChatting.getLastAccessAt()).size());
        }

        return chattingRoomLatestResponseDto;
    }

    public List<ChattingResponseDto> getChattingList(String email, Long cid) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.getById(cid);

        userChattingRepository.findByUserAndChattingRoom(user, chattingRoom)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 들어있지 않습니다."));

        List<Chatting> chattings = chattingRepository.findByChattingRoomOrderBySendAtAsc(chattingRoom);

        List<ChattingResponseDto> chattingResponseDtos = new ArrayList<>();

        for (Chatting chatting : chattings) {
            ChattingResponseDto chattingResponseDto = new ChattingResponseDto();
            chattingResponseDto.setCid(chatting.getChattingRoom().getId());
            chattingResponseDto.setUid(chatting.getUser().getId());
            chattingResponseDto.setMessage(chatting.getMessage());
            chattingResponseDto.setSendAt(chatting.getSendAt());
            chattingResponseDto.setIsRequest(chatting.getIsRequest());
            chattingResponseDtos.add(chattingResponseDto);
        }

        return chattingResponseDtos;
    }

    public Chatting sendRequest(String email, Long cid) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("채팅 방을 찾을 수 없습니다."));

        if (chattingRoom.getRequestUser() != null)
            throw new Exception("이미 요청이 존재합니다.");

        chattingRoom.setRequestAt(new Timestamp(System.currentTimeMillis()));
        chattingRoom.setRequestUser(user);
        chattingRoom.setRequestSuccess(false);
        chattingRoomRepository.save(chattingRoom);

        return chattingRepository.save(Chatting.builder()
                .user(user)
                .chattingRoom(chattingRoom)
                .message("☆저와 함꼐 하시죠☆")
                .sendAt(new Timestamp(System.currentTimeMillis()))
                .isRequest(true)
                .build());
    }

    public Chatting acceptRequest(String email, Long cid) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("채팅 방을 찾을 수 없습니다."));

        if (chattingRoom.getRequestUser() == null)
            throw new Exception("요청이 없습니다.");

        if (chattingRoom.getRequestUser() == user)
            throw new Exception("요청을 보낸 사용자는 요청에 응답할 수 없습니다.");

        chattingRoom.setRequestSuccess(true);
        chattingRoomRepository.save(chattingRoom);

        return chattingRepository.save(Chatting.builder()
                .user(user)
                .chattingRoom(chattingRoom)
                .message("♡좋아요♡")
                .sendAt(new Timestamp(System.currentTimeMillis()))
                .isRequest(false)
                .build());
    }

    public Chatting declineRequest(String email, Long cid) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("채팅 방을 찾을 수 없습니다."));

        if (chattingRoom.getRequestUser() == null)
            throw new Exception("요청이 없습니다.");

        if (chattingRoom.getRequestSuccess() == true)
            throw new Exception("이미 요청이 승인되었습니다.");

        if (chattingRoom.getRequestUser() == user)
            throw new Exception("요청을 보낸 사용자는 요청에 응답할 수 없습니다.");

        chattingRoom.setRequestAt(null);
        chattingRoom.setRequestUser(null);
        chattingRoom.setRequestSuccess(false);
        chattingRoomRepository.save(chattingRoom);

        return chattingRepository.save(Chatting.builder()
                .user(user)
                .chattingRoom(chattingRoom)
                .message("죄송합니다ㅠㅠ")
                .sendAt(new Timestamp(System.currentTimeMillis()))
                .isRequest(false)
                .build());
    }
}
