package com.project.mnm.service;

import com.project.mnm.domain.House;
import com.project.mnm.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
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
}
