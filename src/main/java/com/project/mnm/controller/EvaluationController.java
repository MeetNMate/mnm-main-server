package com.project.mnm.controller;

import com.project.mnm.dto.common.Response;
import com.project.mnm.dto.evaluation.EvaluationInsertDto;
import com.project.mnm.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("")
    public Response createEvaluation(@RequestHeader(value = "X-AUTH-TOKEN") String token, @RequestBody EvaluationInsertDto dto){
        Response response;
        try{
            String message = "평가 등록을 성공적으로 완료했습니다.";
            response = new Response("success", message, evaluationService.addEvaluation(token, dto));
        } catch (Exception e) {
            String message = "평가 등록을 하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response getDoNotEvaluation(@RequestHeader(value = "X-AUTH-TOKEN") String token, @PathVariable("id") int id){
        Response response;
        try{
            String message = "미평가자 조회를 성공적으로 완료했습니다.";
            response = new Response("success", message, evaluationService.findDoNotEvaluate(token, id));
        } catch (Exception e) {
            String message = "미평가자 조회를 하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }

    @GetMapping("profile/{id}")
    public Response getProfileComment(@PathVariable("id") int id){
        Response response;
        try{
            String message = "평가정보 조회를 완료했습니다.";
            response = new Response("success", message, evaluationService.getAllEvaluationsByUid(id));
        } catch (Exception e) {
            String message = "평가정보 조회를 하는 도중 오류가 발생했습니다.";
            response = new Response("failed", message, e.toString());
        }
        return response;
    }
}
