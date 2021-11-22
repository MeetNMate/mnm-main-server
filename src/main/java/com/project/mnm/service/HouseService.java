package com.project.mnm.service;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.House;
import com.project.mnm.dto.HouseInsertDto;
import com.project.mnm.dto.HouseUpdateDto;
import com.project.mnm.repository.ChattingRepository;
import com.project.mnm.repository.HouseRepository;
import com.project.mnm.repository.UserHouseRepository;
import com.project.mnm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UserHouseRepository userHouseRepository;
    private final UserRepository userRepository;
    private final ChattingRepository chattingRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public HouseService(HouseRepository houseRepository, UserHouseRepository userHouseRepository, UserRepository userRepository, ChattingRepository chattingRepository, JwtTokenProvider jwtTokenProvider) {
        this.houseRepository = houseRepository;
        this.userHouseRepository = userHouseRepository;
        this.userRepository = userRepository;
        this.chattingRepository = chattingRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public House createdHouse(HouseInsertDto houseDto) {
//        ChattingRoom chattingRoom = chattingRoomRepository.findById(houseDto.getChattingRoomId());
//        List<Chatting> users = chattingRepository.findByChattingRoom(chattingRoom);
//        User user = userRepository.findById(houseDto.getCaptainId())
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        House house = new House();
//        house.setDescription(houseDto.getDescription());
//        house.setCapacity(houseDto.getCapacity());
//        house.setCaptain(user);
//        house.setLocation(houseDto.getLocation());
//        House resultHouse = houseRepository.save(house);
//
//        UserHouse userHouse = new UserHouse();
//        userHouse.setHouse(resultHouse);
//        userHouse.setUser(loginUser);
//        userHouse.setEliminated(false);
//        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
//        userHouse.setJoinAt(nowTime);
//        userHouse.setLastAccessAt(nowTime);
//        userHouseRepository.save(userHouse);

        return house;
    }

    public Optional<House> findHouseById(long id){
        return houseRepository.findById(id);
    }

    public House updateHouseDetail(HouseUpdateDto dto, long houseId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 하우스입니다."));
        house.setLocation(dto.getLocation());
        house.setCapacity(dto.getCapacity());
        house.setDescription(dto.getDescription());
        house.setName(dto.getName());
        return houseRepository.save(house);
    }
}
