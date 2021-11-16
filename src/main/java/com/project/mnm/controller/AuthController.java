package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.domain.User;
import com.project.mnm.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/join")
    public Response join(@RequestBody User user) {
        Response response = new Response();

        try {
            authService.joinUser(user);
            response.setResponse("success");
            response.setMessage("회원가입을 성공적으로 완료했습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("회원가입을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        Response response = new Response();

        try {
            String jwtToken = authService.loginUser(user);

            response.setResponse("success");
            response.setMessage("로그인을 성공적으로 완료했습니다.");
            response.setData(jwtToken);
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("로그인을 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @DeleteMapping("/remove")
    public Response remove(@RequestBody User user) {
        Response response = new Response();

        try {
            authService.removeUser(user);

            response.setResponse("success");
            response.setMessage("회원 탈퇴를 성공적으로 완료했습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("회원 탈퇴를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }
}
