package com.project.mnm.controller;

import com.project.mnm.dto.common.Response;
import com.project.mnm.service.matching.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MatchingController {
    private final MatchingService matchingService;

    @Autowired
    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/{email}/use/matching")
    public Response isUseMatching(@PathVariable("email") String email) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setData(matchingService.isUseMatching(email));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setData(e.toString());
        }

        return response;
    }

    @PutMapping("/{email}/use/matching/{use_matching}")
    public Response changeUseMatching(@PathVariable("email") String email,
                                      @PathVariable("use_matching") Boolean use_matching) {
        Response response = new Response();

        matchingService.changeUseMatching(email, use_matching);

        try {
            response.setResponse("success");
            response.setMessage("매칭 기능 사용 여부를 변경하였습니다.");
            response.setData(use_matching);
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("매칭 기능 사용 여부를 변경하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @GetMapping("/{email}/matching/result")
    public Response getMatchingResultList(@PathVariable("email") String email,
                                          @RequestBody List<Long> resultIdList) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("매칭 결과를 가져오는 데 성공하였습니다.");
            response.setData(matchingService.getMatchingResultList(resultIdList));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("매칭 결과를 가져오는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}
