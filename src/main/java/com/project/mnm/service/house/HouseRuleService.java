package com.project.mnm.service.house;

import com.project.mnm.domain.House;
import com.project.mnm.domain.HouseRule;
import com.project.mnm.domain.User;
import com.project.mnm.dto.house.role.HouseRoleResponseDto;
import com.project.mnm.dto.house.rule.HouseRuleInsertDto;
import com.project.mnm.dto.house.rule.HouseRuleResponseDto;
import com.project.mnm.dto.house.rule.HouseRuleUpdateDto;
import com.project.mnm.repository.HouseRepository;
import com.project.mnm.repository.HouseRuleRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseRuleService {
    private final HouseRuleRepository houseRuleRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Autowired
    public HouseRuleService(HouseRuleRepository houseRuleRepository, HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRuleRepository = houseRuleRepository;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    public HouseRule saveHouseRule(HouseRuleInsertDto dto) {
        HouseRule houseRule = new HouseRule();
        houseRule.setNewRule(dto.getRule());
        houseRule.setOriginalRule(dto.getRule());
        houseRule.setUpperNum(dto.getUpperNum());
        houseRule.setLowerNum(dto.getLowerNum());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        House house = houseRepository.findById(dto.getHouseId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 하우스입니다."));

        houseRule.setUser(user);
        houseRule.setHouse(house);
        return houseRuleRepository.save(houseRule);
    }

    public List<HouseRuleResponseDto> findAllHouseRules(long houseId) {
        List<HouseRule> rules = houseRuleRepository.findByHouse(houseRepository.findById(houseId));
        List<HouseRuleResponseDto> results = new ArrayList<>();
        for (HouseRule rule : rules) {
            HouseRuleResponseDto dto = new HouseRuleResponseDto(rule.getId(), rule.getHouse().getId(), rule.getOriginalRule(), rule.getUpperNum(), rule.getLowerNum());
            results.add(dto);
        }
        return results;
    }

    public HouseRule modifyHouseRule(long ruleId, HouseRuleUpdateDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        HouseRule houseRule = houseRuleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("존재한지 않는 룰입니다."));

        if (dto.getRule() != null) {
            houseRule.setNewRule(dto.getRule());
        }
        houseRule.setUser(user);
        houseRule.setLowerNum(dto.getLowerNum());

        return houseRuleRepository.save(houseRule);
    }

    public void removeHouseRule(long ruleId) {
        HouseRule houseRule = houseRuleRepository.findById(ruleId)
                .orElseThrow(() -> new IllegalArgumentException("존재한지 않는 룰입니다."));
        houseRuleRepository.delete(houseRule);
    }
}
