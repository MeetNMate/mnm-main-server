package com.project.mnm.controller;

import com.project.mnm.domain.House;
import com.project.mnm.domain.User;
import com.project.mnm.dto.HouseInsertDto;
import com.project.mnm.repository.UserRepository;
import com.project.mnm.service.HouseService;
import com.project.mnm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("house")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("")
    @ResponseBody
    public String readAllHouse(){
//        return houseService.findHouseByUserId(1);
        return "준비중";
    }

    @PostMapping("")
    @ResponseBody
    public String createHouse(@RequestBody HouseInsertDto houseDto){
        House house = new House();
        house.setDescription(houseDto.getDescription());
        house.setCapacity(houseDto.getCapacity());
//        User user = UserRepository.
//        house.setCaptainId(houseDto.getCaptainId());
        house.setLocation(houseDto.getLocation());
        houseService.createdHouse(house);
        return houseDto.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<House> readHouse(@PathVariable("id") int id){
        return houseService.findHouseById(id);
    }
}
