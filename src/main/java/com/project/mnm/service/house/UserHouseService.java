package com.project.mnm.service.house;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.House;
import com.project.mnm.domain.User;
import com.project.mnm.domain.UserHouse;
import com.project.mnm.repository.HouseRepository;
import com.project.mnm.repository.UserHouseRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserHouseService {
    private final UserHouseRepository userHouseRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserHouseService(UserHouseRepository userHouseRepository, HouseRepository houseRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userHouseRepository = userHouseRepository;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void modifyUserHouseState(long houseId, String token) {
        String userEmail = jwtTokenProvider.getUserPk(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 하우스입니다."));
        UserHouse userHouse = userHouseRepository.findByUserAndHouse(user, house);
        userHouse.setExitAt(new Timestamp(System.currentTimeMillis()));
        userHouseRepository.save(userHouse);
        // 평가를 완료하면 true로 변경된다
    }

    public List<House> findAllHouses(String token) {
        String userEmail = jwtTokenProvider.getUserPk(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        List<UserHouse> list = userHouseRepository.findAllByUser(user);
        List<House> houseList = new ArrayList<>();
        if (list == null) {
            return null;
        }
        for (UserHouse item : list) {
            if(item.getExitAt() == null) houseList.add(item.getHouse());
        }
        return houseList;
    }
}
