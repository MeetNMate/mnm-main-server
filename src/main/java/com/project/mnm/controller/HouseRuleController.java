package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.dto.HouseRuleInsertDto;
import com.project.mnm.service.HouseRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
