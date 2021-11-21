package com.project.mnm.controller;

import com.project.mnm.domain.House;
import com.project.mnm.domain.Response;
import com.project.mnm.domain.User;
import com.project.mnm.dto.HouseInsertDto;
import com.project.mnm.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house")
public class HouseController {
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("")
    public Response createHouse(@RequestBody HouseInsertDto houseDto) {
        Response response = new Response();
        House house = new House();
        house.setDescription(houseDto.getDescription());
        house.setCapacity(houseDto.getCapacity());
//        User user = UserRepository.
//        house.setCaptainId(houseDto.getCaptainId());
        house.setLocation(houseDto.getLocation());

        try {
            response.setResponse("success");
            response.setMessage("하우스 생성을 성공적으로 완료했습니다.");
            response.setData(houseService.createdHouse(house));
        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("하우스 생성을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @GetMapping("")
    public Response readAllHouse() {
        User user = new User();
        Response response;
        try {
            String message = "하우스 목록 조회를 성공적으로 완료했습니다.";
            response = new Response("success", message, houseService.findHouseByUserId(user.getId()));
        } catch (Exception e) {
            String message = "하우스 목록 조회를 하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Response readHouse(@PathVariable("id") int id) {
        Response response;
        try {
            String message = "하우스 조회를 성공적으로 완료했습니다.";
            response = new Response("success", message, houseService.findHouseById(id));
        } catch (Exception e) {
            String message = "하우스 조회를 하는 도중 오류가 발생했습니다.";
            response = new Response("success", message, e.toString());
        }
        return response;
    }
}
