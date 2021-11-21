package com.project.mnm.service;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.Evaluation;
import com.project.mnm.domain.House;
import com.project.mnm.domain.Profile;
import com.project.mnm.domain.User;
import com.project.mnm.dto.EvaluationInsertDto;
import com.project.mnm.repository.EvaluationRepository;
import com.project.mnm.repository.HouseRepository;
import com.project.mnm.repository.ProfileRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HouseRepository houseRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, HouseRepository houseRepository, ProfileRepository profileRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.houseRepository = houseRepository;
        this.profileRepository = profileRepository;
    }

    public void addEvaluation(String userToken, EvaluationInsertDto dto) {
        String userEmail = jwtTokenProvider.getUserPk(userToken);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        House house = houseRepository.findById(dto.getHouseId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 하우스입니다."));
        Evaluation evaluation = new Evaluation();
        evaluation.setAppraiser(user);
        evaluation.setAppraisee(userRepository.getById(dto.getAppraiseeId()));
        evaluation.setHouse(house);
        evaluation.setContent(dto.getContent());
        evaluation.setScore(dto.getScore());
        evaluationRepository.save(evaluation);
    }

    public void applyScoreToProfile(String userToken) {
        String userEmail = jwtTokenProvider.getUserPk(userToken);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        List<Evaluation> list = evaluationRepository.findAllByAppraisee(user);
        int totalScore = 0;
        int count = 0;
        for (Evaluation item : list) {
            totalScore += item.getScore();
            count++;
        }
        totalScore /= count;
        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        profile.setScore(totalScore);
        profileRepository.save(profile);
    }
}
