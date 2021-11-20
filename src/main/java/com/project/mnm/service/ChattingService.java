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
}
