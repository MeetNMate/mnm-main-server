package com.project.mnm.service;

import com.project.mnm.domain.User;
import com.project.mnm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchingService {

    private final UserRepository userRepository;

    public Boolean isUseMatching(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        return user.isUseMatching();
    }

    public void changeUseMatching(String email, Boolean use_matching) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        user.setUseMatching(use_matching);
        userRepository.save(user);
    }

    public List<User> getMatchingResultList(List<Long> resultIdList) {
        List<User> resultUserList = new ArrayList<User>();

        for (Long id : resultIdList) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

            resultUserList.add(userRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다.")));
        }

        return resultUserList;
    }
}
