package com.project.mnm.service;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.*;
import com.project.mnm.dto.EvaluationInsertDto;
import com.project.mnm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final HouseRepository houseRepository;
    private final ProfileRepository profileRepository;
    private final UserHouseRepository userHouseRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider, HouseRepository houseRepository, ProfileRepository profileRepository, UserHouseRepository userHouseRepository) {
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.houseRepository = houseRepository;
        this.profileRepository = profileRepository;
        this.userHouseRepository = userHouseRepository;
    }

    public Evaluation addEvaluation(String userToken, EvaluationInsertDto dto) {
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
        Evaluation result = evaluationRepository.save(evaluation);
//        applyScoreToProfile(userToken);
        applyScoreToProfile(dto.getAppraiseeId());
        return result;
    }

    // 평가 받은 사람으로 조회해야하는 데 반대로 해놨길래 바꿨어요.
    public void applyScoreToProfile(Long appraiseeId) {
        User user = userRepository.findById(appraiseeId)
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

//    public void applyScoreToProfile(String userToken) {
//        String userEmail = jwtTokenProvider.getUserPk(userToken);
//        User user = userRepository.findByEmail(userEmail)
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
//        List<Evaluation> list = evaluationRepository.findAllByAppraisee(user);
//        System.out.println("===="+user.getId()+""+list);
//        int totalScore = 0;
//        int count = 0;
//        for (Evaluation item : list) {
//            totalScore += item.getScore();
//            count++;
//        }
//        totalScore /= count;
//        Profile profile = profileRepository.findByUser(user)
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
//
//        profile.setScore(totalScore);
//        profileRepository.save(profile);
//    }

    public List<User> findDoNotEvaluate(String userToken, long houseId) {
        String userEmail = jwtTokenProvider.getUserPk(userToken);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));

        List<UserHouse> list = userHouseRepository.findAllByHouse(house);
        Set<User> houseUsers = new HashSet<>();
        for (UserHouse item : list) {
            houseUsers.add(item.getUser());
        }
        houseUsers.remove(user);
        List<Evaluation> evaluations = evaluationRepository.findAllByHouseAndAppraiser(house, user);
        for (Evaluation item : evaluations) {
            houseUsers.remove(item.getAppraisee());
        }

        return new ArrayList<>(houseUsers);
    }
}
