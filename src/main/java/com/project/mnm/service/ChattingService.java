package com.project.mnm.service;

import com.project.mnm.domain.Chatting;
import com.project.mnm.domain.User;
import com.project.mnm.repository.ChattingRepository;
import com.project.mnm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChattingService {
    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;

    public Chatting chattingHandler(Chatting chatting) {
        User user = userRepository.findById(chatting.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        chatting.setUser(user);

        return chattingRepository.save(chatting);
    }

//    public Chatting makeChattingRoom(Chatting chatting) {
//        User user = userRepository.findById(chatting.getUser().getId())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
//
//        chatting.setUser(user);
//        chatting.setMessage("안녕하세요:D");
//
//        ChattingRoom chattingRoom = chattingRoomRepository.save(ChattingRoom.builder().build());
//
//        userChattingRepository.save(UserChatting.builder()
//                .user(user)
//                .chattingRoom(chattingRoom)
//                .lastAccessAt(chatting.getSendAt())
//                .build());
//
//        return chattingRepository.save(chatting);
//    }
}
