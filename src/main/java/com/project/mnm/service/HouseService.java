package com.project.mnm.service;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.House;
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

    public House createdHouse(House house){
        return houseRepository.save(house);
    }

    public Optional<House> findHouseById(long id){
        return houseRepository.findById(id);
    }

//    public List<House> findHouseByUserId(long id){
//        return houseRepository.findHousesByUserId(id);
//    }

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
