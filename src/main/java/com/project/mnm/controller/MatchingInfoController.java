package com.project.mnm.controller;

import com.project.mnm.domain.MatchingInfo;
import com.project.mnm.domain.Response;
import com.project.mnm.service.MatchingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchingInfoController {
    @Autowired
    private MatchingInfoService matchingInfoService;

    @GetMapping("/matchinginfo/{uid}")
    public Response getResidenceInfo(@PathVariable("uid") Long uid) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("매칭정보 조회를 성공적으로 완료했습니다.");
            response.setData(matchingInfoService.getMatchingInfo(uid));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("매칭정보 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PostMapping("/matchinginfo")
    public Response addResidenceInfo(@RequestBody MatchingInfo info) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("매칭정보 등록을 성공적으로 완료했습니다.");
            response.setData(matchingInfoService.addMatchingInfo(info));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("매칭정보 등록을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PutMapping("/matchinginfo/{uid}") // 전체 수정만 가능
    public Response updateMatchingInfo(@PathVariable("uid") Long uid,
                                        @RequestBody MatchingInfo info) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("매칭정보 수정을 성공적으로 완료했습니다.");
            response.setData(matchingInfoService.updateMatchingInfo(uid, info));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("매칭정보 수정을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @DeleteMapping("/matchinginfo/{uid}")
    public Response deleteResidenceInfo(@PathVariable("uid") Long uid) {
        Response response = new Response();

        matchingInfoService.deleteMatchingInfo(uid);

        try {
            response.setResponse("success");
            response.setMessage("매칭정보 삭제를 성공적으로 완료했습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("매칭정보 삭제를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}
