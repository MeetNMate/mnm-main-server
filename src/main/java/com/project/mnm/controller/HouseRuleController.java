package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.dto.HouseRuleInsertDto;
import com.project.mnm.dto.HouseRuleUpdateDto;
import com.project.mnm.service.HouseRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rule")
public class HouseRuleController {
    private final HouseRuleService houseRuleService;

    @Autowired
    public HouseRuleController(HouseRuleService houseRuleService) {
        this.houseRuleService = houseRuleService;
    }

    @PostMapping
    public Response createRule(@RequestBody HouseRuleInsertDto dto) {
        Response response;
        try {
            String message = "하우스 룰 등록을 성공적으로 완료했습니다.";
            response = new Response("success", message, houseRuleService.createHouseRule(dto));
        } catch (Exception e) {
            String message = "하우스 룰을 등록하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @GetMapping("/house/{id}")
    public Response readRules(@PathVariable("id") int houseId) {
        Response response;
        try {
            String message = "하우스 룰 조회를 성공적으로 완료했습니다.";
            response = new Response("success", message, houseRuleService.findHouseRuleByHouseId(houseId));
        } catch (Exception e) {
            String message = "하우스 룰을 조회하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @PatchMapping("/{id}")
    public Response updateRule(@PathVariable("id") int ruleId, @RequestBody HouseRuleUpdateDto dto){
        Response response;
        try {
            String message = "하우스 룰 수정을 성공적으로 완료했습니다.";
            response = new Response("success", message, houseRuleService.updateHouseRule(ruleId, dto));
        } catch (Exception e) {
            String message = "하우스 룰을 수정하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public Response deleteRule(@PathVariable("id") int ruleId){
        Response response;
        try {
            String message = "하우스 룰 삭제를 성공적으로 완료했습니다.";
            houseRuleService.deleteHouseRule(ruleId);
            response = new Response("success", message, "");
        } catch (Exception e) {
            String message = "하우스 룰을 삭제하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }
}
