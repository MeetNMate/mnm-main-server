package com.project.mnm.controller;

import com.project.mnm.domain.House;
import com.project.mnm.domain.User;
import com.project.mnm.dto.HouseInsertDto;
import com.project.mnm.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<House> readAllHouse(){
        User user = new User();
        return houseService.findHouseByUserId(user.getId());
    }

    @PostMapping("")
    @ResponseBody
    public House createHouse(@RequestBody HouseInsertDto houseDto){
        House house = new House();
        house.setDescription(houseDto.getDescription());
        house.setCapacity(houseDto.getCapacity());
//        User user = UserRepository.
//        house.setCaptainId(houseDto.getCaptainId());
        house.setLocation(houseDto.getLocation());
        return houseService.createdHouse(house);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<House> readHouse(@PathVariable("id") int id){
        return houseService.findHouseById(id);
    }
}
