package com.project.mnm.controller;

import com.project.mnm.config.JwtTokenProvider;
import com.project.mnm.dto.common.Response;
import com.project.mnm.dto.profile.ProfileInsertDto;
import com.project.mnm.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/user/profile")
@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{uid}")
    public Response getProfile(@PathVariable("uid") Long uid) {
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("프로필 조회를 성공적으로 완료했습니다.");
            response.setData(profileService.getProfile(uid));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("프로필 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PostMapping("")
    public Response addProfile(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                               @RequestBody ProfileInsertDto profileInsertDto) {
        Response response = new Response();

        try {
            String email = jwtTokenProvider.getUserPk(token);
            response.setResponse("success");
            response.setMessage("프로필 등록을 성공적으로 완료했습니다.");
            response.setData(profileService.addProfile(email, profileInsertDto));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("프로필 등록을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PutMapping("")
    public Response updateProfile(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                  @RequestParam("image") MultipartFile imageFile,
                                  @RequestParam("name") String name,
                                  @RequestParam("sex") String sex,
                                  @RequestParam("age") Integer age,
                                  @RequestParam("score") Integer score,
                                  @RequestParam("description") String description) {
        Response response = new Response();

        try {
            String email = jwtTokenProvider.getUserPk(token);
            response.setResponse("success");
            response.setMessage("프로필 수정을 성공적으로 완료했습니다.");
            response.setData(profileService.updateProfile(email, imageFile, name, sex, age, score, description));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("프로필 수정을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PatchMapping("")
    public Response editProfile(@RequestHeader(value = "X-AUTH-TOKEN") String token,
                                  @RequestParam(value = "image", required = false) MultipartFile imageFile,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "sex", required = false) String sex,
                                  @RequestParam(value = "age", required = false) Integer age,
                                  @RequestParam(value = "score", required = false) Integer score) {
        Response response = new Response();

        try {
            String email = jwtTokenProvider.getUserPk(token);
            response.setResponse("success");
            response.setMessage("프로필 수정을 성공적으로 완료했습니다.");
            response.setData(profileService.editProfile(email, imageFile, name, sex, age, score));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("프로필 수정을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @DeleteMapping("")
    public Response deleteProfile(@RequestHeader(value = "X-AUTH-TOKEN") String token) {
        Response response = new Response();

        profileService.deleteProfile(jwtTokenProvider.getUserPk(token));

        try {
            response.setResponse("success");
            response.setMessage("프로필 삭제를 성공적으로 완료했습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("프로필 삭제를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}
