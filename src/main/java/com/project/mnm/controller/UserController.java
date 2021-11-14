package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.domain.User;
import com.project.mnm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/join")
    public Response join(@RequestBody User user) {
        Response response = new Response();

        try {
            userService.joinUser(user);
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

    // 로그인
    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        Response response = new Response();

        try {
            String jwtToken = userService.loginUser(user);

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
}
