package com.project.mnm.service;

import com.project.mnm.domain.House;
import com.project.mnm.domain.HouseRole;
import com.project.mnm.domain.User;
import com.project.mnm.dto.HouseRoleInsertDto;
import com.project.mnm.dto.HouseRoleUpdateDto;
import com.project.mnm.repository.HouseRepository;
import com.project.mnm.repository.HouseRoleRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseRoleService {
    private final HouseRoleRepository houseRoleRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Autowired
    public HouseRoleService(HouseRoleRepository houseRoleRepository, HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRoleRepository = houseRoleRepository;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    public HouseRole createHouseRole(HouseRoleInsertDto dto) {
        HouseRole houseRole = new HouseRole();
        houseRole.setRole(dto.getRole());
        houseRole.setWeek(dto.getWeek());
        houseRole.setRoutine(dto.getRoutine());
        houseRole.setStartAt(dto.getStartAt());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        House house = houseRepository.findById(dto.getHouseId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 하우스입니다."));

        houseRole.setUser(user);
        houseRole.setHouse(house);
        return houseRoleRepository.save(houseRole);
    }

    public List<HouseRole> findHouseRolesByHouseId(long houseId) {
        return houseRoleRepository.findByHouse(houseRepository.findById(houseId));
    }

    public HouseRole updateHouseRule(long roleId, HouseRoleUpdateDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        HouseRole houseRole = houseRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("존재한지 않는 롤입니다."));

        if (dto.getRole() != null) {
            houseRole.setRole(dto.getRole());
        }
        houseRole.setUser(user);
        houseRole.setWeek(dto.getWeek());
        houseRole.setRoutine(dto.getRoutine());

        return houseRoleRepository.save(houseRole);
    }

    public void deleteHouseRule(long roleId) {
        HouseRole houseRole = houseRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("존재한지 않는 룰입니다."));
        houseRoleRepository.delete(houseRole);
    }
}
