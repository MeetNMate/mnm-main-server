package com.project.mnm.service;

import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.ChattingRoom;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserChatting;
import com.project.mnm.dto.ChattingInsertDto;
import com.project.mnm.dto.ChattingResponseDto;
import com.project.mnm.dto.ChattingRoomInsertDto;
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

    public Chatting chattingHandler(Long cid, ChattingInsertDto chattingInsertDto) {
        System.out.println(cid+""+chattingInsertDto);
        User user = userRepository.findById(chattingInsertDto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        userChattingRepository.findByUserAndChattingRoom(user, chattingRoom)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 참여하고 있지 않습니다."));

        Chatting chatting = new Chatting();
        chatting.setUser(user);
        chatting.setChattingRoom(chattingRoom);
        chatting.setMessage(chattingInsertDto.getMessage());
        chatting.setSendAt(chattingInsertDto.getSendAt());
        chatting.setIsRequest(chattingInsertDto.getIsRequest());

        if (chatting.getIsRequest()) { // 메이트 요청일 경우
            chattingRoom.setRequestAt(chatting.getSendAt());
            chattingRoom.setRequestUser(user);
            chattingRoom.setRequestSuccess(false);
        }

        return chattingRepository.save(chatting);
    }

    public Chatting makeChattingRoom(ChattingRoomInsertDto chattingRoomInsertDto) {
        User sender = userRepository.findById(chattingRoomInsertDto.getSenderUid())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        User receiver = userRepository.findById(chattingRoomInsertDto.getReceiverUid())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.save(new ChattingRoom());

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

    public List<ChattingResponseDto> getChattingList(String email, Long cid) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.getById(cid);

        userChattingRepository.findByUserAndChattingRoom(user, chattingRoom)
                .orElseThrow(() -> new IllegalArgumentException("해당 채팅방에 들어있지 않습니다."));

        List<Chatting> chattings = chattingRepository.findByChattingRoomOrderBySendAtDesc(chattingRoom);

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
}
