package com.project.mnm.service;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.domain.*;
import com.project.mnm.dto.house.lobby.HouseInsertDto;
import com.project.mnm.dto.house.lobby.HouseResponseDto;
import com.project.mnm.dto.house.lobby.HouseUpdateDto;
import com.project.mnm.dto.house.common.UserResponseDto;
import com.project.mnm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UserHouseRepository userHouseRepository;
    private final UserRepository userRepository;
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserChattingRepository userChattingRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ProfileRepository profileRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository, UserHouseRepository userHouseRepository, UserRepository userRepository, ChattingRepository chattingRepository, ChattingRoomRepository chattingRoomRepository, UserChattingRepository userChattingRepository, JwtTokenProvider jwtTokenProvider, ProfileRepository profileRepository) {
        this.houseRepository = houseRepository;
        this.userHouseRepository = userHouseRepository;
        this.userRepository = userRepository;
        this.chattingRoomRepository = chattingRoomRepository;
        this.userChattingRepository = userChattingRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.profileRepository = profileRepository;
    }

    public House createdHouse(HouseInsertDto houseDto, String token) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(houseDto.getChattingRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        String userEmail = jwtTokenProvider.getUserPk(token);
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 사용자입니다."));
        List<UserChatting> userChattingList = userChattingRepository.findByChattingRoom(chattingRoom);
        House house = new House();
        house.setDescription(houseDto.getDescription());
        house.setCapacity(houseDto.getCapacity());
        house.setLocation(houseDto.getLocation());
        house.setFirstCaptain(userChattingList.get(0).getUser());
        house.setSecondCaptain(userChattingList.get(1).getUser());
        House resultHouse = houseRepository.save(house);
        for(UserChatting item :userChattingList){
            UserHouse userHouse = new UserHouse();
            userHouse.setHouse(resultHouse);
            userHouse.setUser(item.getUser());
            userHouse.setEliminated(false);
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            userHouse.setJoinAt(nowTime);
            userHouse.setLastAccessAt(nowTime);
            userHouseRepository.save(userHouse);
        }
        return house;
    }

    public HouseResponseDto findHouseById(long id){
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 하우스입니다."));
        HouseResponseDto dto = new HouseResponseDto();
        dto.setId(house.getId());
        dto.setName(house.getName());
        dto.setDescription(house.getDescription());
        dto.setLocation(house.getLocation());
        dto.setUsers(new ArrayList<>());
        List<UserHouse> list = userHouseRepository.findAllByHouse(house);
        for (UserHouse item :list){
            Profile profile = profileRepository.findByUser(item.getUser())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 프로필입니다."));
            dto.getUsers().add(new UserResponseDto(profile.getId(), profile.getImage(), profile.getName()));
        }
        return dto;
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
