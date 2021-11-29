package com.project.mnm.service.user;

import com.project.mnm.domain.ResidenceInfo;
import com.project.mnm.domain.User;
import com.project.mnm.repository.ResidenceInfoRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResidenceInfoService {
    private final ResidenceInfoRepository residenceInfoRepository;
    private final UserRepository userRepository;

    @Autowired
    public ResidenceInfoService(ResidenceInfoRepository residenceInfoRepository, UserRepository userRepository) {
        this.residenceInfoRepository = residenceInfoRepository;
        this.userRepository = userRepository;
    }

    public ResidenceInfo findResidenceInfo(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        return residenceInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("거주정보가 등록되어있지 않습니다."));
    }

    public ResidenceInfo addResidenceInfo(Long uid, Integer headcount, String location,
                                          Integer budget, Timestamp termStart, Timestamp termEnd) throws Exception {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        if (residenceInfoRepository.findByUser(user).isPresent())
            throw new Exception("이미 거주정보가 등록되어 있습니다.");

        return residenceInfoRepository.save(ResidenceInfo.builder()
                .user(user)
                .headcount(headcount)
                .location(location)
                .budget(budget)
                .termStart(termStart)
                .termEnd(termEnd)
                .build());
    }

    public ResidenceInfo modifyResidenceInfo(Long uid, ResidenceInfo info) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ResidenceInfo residenceInfo = residenceInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("거주정보가 등록되어있지 않습니다."));

        if (info.getHeadcount() != 0)
            residenceInfo.setHeadcount(info.getHeadcount());
        if (info.getLocation() != null)
            residenceInfo.setLocation(info.getLocation());
        if (info.getBudget() != 0)
            residenceInfo.setBudget(info.getBudget());
        if (info.getTermStart() != null)
            residenceInfo.setTermStart(info.getTermStart());
        if (info.getTermEnd() != null)
            residenceInfo.setTermEnd(info.getTermEnd());

        return residenceInfoRepository.save(residenceInfo);
    }

    public void removeResidenceInfo(Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        ResidenceInfo info = residenceInfoRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("거주정보가 등록되어있지 않습니다."));

        residenceInfoRepository.delete(info);
    }
}
