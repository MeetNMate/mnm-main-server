package com.project.mnm.controller;

import com.project.mnm.domain.House;
import com.project.mnm.domain.Response;
import com.project.mnm.dto.HouseInsertDto;
import com.project.mnm.dto.HouseUpdateDto;
import com.project.mnm.service.HouseService;
import com.project.mnm.service.UserHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/house")
public class HouseController {
    private final HouseService houseService;
    private final UserHouseService userHouseService;

    @Autowired
    public HouseController(HouseService houseService, UserHouseService userHouseService) {
        this.houseService = houseService;
        this.userHouseService = userHouseService;
    }

    @PostMapping("")
    public Response createHouse(@RequestHeader(value = "X-AUTH-TOKEN") String token, @RequestBody HouseInsertDto houseDto) {
        Response response;
        try {
            String message = "하우스 생성을 성공적으로 완료했습니다.";
            response = new Response("success", message, houseService.createdHouse(houseDto, token));
        } catch (Exception e) {
            String message = "하우스 생성을 하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }

        return response;
    }

    @GetMapping("")
    public Response readAllHouse(@RequestHeader(value = "X-AUTH-TOKEN") String token) {
        Response response;
        try {
            String message;
            List<House> result = userHouseService.getAllHouseByUser(token);
            if (result == null) {
                message = "소속된 하우스가 없습니다.";
            } else {
                message = "하우스 목록 조회를 성공적으로 완료했습니다.";
            }
            response = new Response("success", message, result);
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

    @PatchMapping("/{id}")
    public Response editHouse(@PathVariable("id") int id, @RequestBody HouseUpdateDto dto) {
        Response response;
        try {
            String message = "하우스 수정을 성공적으로 완료했습니다.";
            response = new Response("success", message, houseService.updateHouseDetail(dto, id));
        } catch (Exception e) {
            String message = "하우스 수정을 하는 도중 오류가 발생했습니다.";
            response = new Response("success", message, e.toString());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public Response exitHouse(@RequestHeader(value = "X-AUTH-TOKEN") String token, @PathVariable("id") int id) {
        Response response;
        try {
            String message = "하우스 나가기를 성공적으로 완료했습니다.";
            userHouseService.exitUserFromHouse(id, token);
            response = new Response("success", message, "");
        } catch (Exception e) {
            String message = "하우스 나가는 도중 오류가 발생했습니다.";
            response = new Response("success", message, e.toString());
        }
        return response;
    }
}
