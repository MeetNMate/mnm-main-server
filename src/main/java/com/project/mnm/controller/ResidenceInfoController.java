package com.project.mnm.controller;

import com.project.mnm.domain.ResidenceInfo;
import com.project.mnm.dto.common.Response;
import com.project.mnm.service.user.ResidenceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/residenceinfo")
@RestController
public class ResidenceInfoController {
    private final ResidenceInfoService residenceInfoService;

    @Autowired
    public ResidenceInfoController(ResidenceInfoService residenceInfoService) {
        this.residenceInfoService = residenceInfoService;
    }

    @GetMapping("/{uid}")
    public Response getResidenceInfo(@PathVariable("uid") Long uid) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("거주정보 조회를 성공적으로 완료했습니다.");
            response.setData(residenceInfoService.findResidenceInfo(uid));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("거주정보 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PostMapping("")
    public Response addResidenceInfo(@RequestBody ResidenceInfo info) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("거주정보 등록을 성공적으로 완료했습니다.");
            response.setData(info);
            response.setData(residenceInfoService.addResidenceInfo(info.getUser().getId(),
                    info.getHeadcount(), info.getLocation(), info.getBudget(), info.getTermStart(), info.getTermEnd()));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("거주정보 등록을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PutMapping("/{uid}") // 전체, 일부 수정 모두 가능
    public Response updateResidenceInfo(@PathVariable("uid") Long uid,
                                        @RequestBody ResidenceInfo info) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("거주정보 수정을 성공적으로 완료했습니다.");
            response.setData(residenceInfoService.modifyResidenceInfo(uid, info));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("거주정보 수정을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @DeleteMapping("/{uid}")
    public Response deleteResidenceInfo(@PathVariable("uid") Long uid) {
        Response response = new Response();

        residenceInfoService.removeResidenceInfo(uid);

        try {
            response.setResponse("success");
            response.setMessage("거주정보 삭제를 성공적으로 완료했습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("거주정보 삭제를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}
