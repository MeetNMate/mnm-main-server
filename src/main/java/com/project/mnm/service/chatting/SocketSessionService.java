package com.project.mnm.service.chatting;

import com.project.mnm.domain.ChattingRoom;
import com.project.mnm.domain.SocketSession;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserChatting;
import com.project.mnm.repository.ChattingRoomRepository;
import com.project.mnm.repository.SocketSessionRepository;
import com.project.mnm.repository.UserChattingRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class SocketSessionService {
    @Autowired
    private SocketSessionRepository socketSessionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChattingRoomRepository chattingRoomRepository;
    @Autowired
    private UserChattingRepository userChattingRepository;

    public void addSession(Long cid, Long uid, String id) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ChattingRoom chattingRoom = chattingRoomRepository.findById(cid)
                .orElseThrow(() -> new IllegalArgumentException("채팅 방을 찾을 수 없습니다."));

        SocketSession socketSession = new SocketSession();
        socketSession.setUser(user);
        socketSession.setChattingRoom(chattingRoom);
        socketSession.setId(id);
        socketSessionRepository.save(socketSession);
    }

    public void deleteSession(String id) {
        SocketSession socketSession = socketSessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("소켓 세션을 찾을 수 없습니다."));

        UserChatting userChatting = userChattingRepository.findByUserAndChattingRoom(socketSession.getUser(), socketSession.getChattingRoom())
                .orElseThrow(() -> new IllegalArgumentException("소켓 세션을 찾을 수 없습니다."));

        userChatting.setLastAccessAt(new Timestamp(System.currentTimeMillis()));
        userChattingRepository.save(userChatting);
        socketSessionRepository.delete(socketSession);
    }
}
