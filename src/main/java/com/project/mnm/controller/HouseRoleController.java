package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.dto.HouseRoleInsertDto;
import com.project.mnm.dto.HouseRoleUpdateDto;
import com.project.mnm.service.HouseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class HouseRoleController {
    private final HouseRoleService houseRoleService;

    @Autowired
    public HouseRoleController(HouseRoleService houseRoleService) {
        this.houseRoleService = houseRoleService;
    }

    @PostMapping
    public Response createRule(@RequestBody HouseRoleInsertDto dto) {
        Response response;
        try {
            String message = "하우스 롤 등록을 성공적으로 완료했습니다.";
            response = new Response("success", message, houseRoleService.createHouseRole(dto));
        } catch (Exception e) {
            String message = "하우스 롤을 등록하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @GetMapping("/house/{id}")
    public Response readRules(@PathVariable("id") int houseId) {
        Response response;
        try {
            String message = "하우스 롤 조회를 성공적으로 완료했습니다.";
            response = new Response("success", message, houseRoleService.findHouseRolesByHouseId(houseId));
        } catch (Exception e) {
            String message = "하우스 롤을 조회하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @PatchMapping("/{id}")
    public Response updateRule(@PathVariable("id") int roleId, @RequestBody HouseRoleUpdateDto dto){
        Response response;
        try {
            String message = "하우스 롤 수정을 성공적으로 완료했습니다.";
            response = new Response("success", message, houseRoleService.updateHouseRule(roleId, dto));
        } catch (Exception e) {
            String message = "하우스 롤을 수정하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public Response deleteRule(@PathVariable("id") int roleId){
        Response response;
        try {
            String message = "하우스 롤 삭제를 성공적으로 완료했습니다.";
            houseRoleService.deleteHouseRule(roleId);
            response = new Response("success", message, "");
        } catch (Exception e) {
            String message = "하우스 롤을 삭제하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }
}
