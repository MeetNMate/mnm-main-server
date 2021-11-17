package com.project.mnm.service;

import com.project.mnm.domain.MatchingInfo;
import com.project.mnm.domain.User;
import com.project.mnm.repository.MatchingInfoRepository;
import com.project.mnm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchingInfoService {
    private final MatchingInfoRepository matchingInfoRepository;
    private final UserRepository userRepository;

    public MatchingInfo getMatchingInfo(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        return matchingInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("매칭정보가 등록되어있지 않습니다."));
    }

    public MatchingInfo addMatchingInfo(MatchingInfo info) throws Exception {
        User user = userRepository.findById(info.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        if (!matchingInfoRepository.findByUser(user).isEmpty())
            throw new Exception("이미 매칭정보가 등록되어 있습니다.");

        info.setUser(user);

        return matchingInfoRepository.save(info);
    }

    public MatchingInfo updateMatchingInfo(Long uid, MatchingInfo info) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        MatchingInfo matchingInfo = matchingInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("매칭정보가 등록되어있지 않습니다."));

        info.setId(matchingInfo.getId());
        info.setUser(user);
        return matchingInfoRepository.save(info);
    }

    public void deleteMatchingInfo(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        MatchingInfo info = matchingInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("매칭정보가 등록되어있지 않습니다."));

        matchingInfoRepository.delete(info);
    }
}
