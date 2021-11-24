package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.domain.User;
import com.project.mnm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User get(@PathVariable("email") String email) {
        return userService.getUser(email);
    }

    @DeleteMapping("")
    public Response deleteAll() {
        Response response = new Response();

        try {
            userService.deleteAllUsers();

            response.setResponse("success");
            response.setMessage("모든 회원을 삭제하였습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("모든 회원 삭제를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

    @DeleteMapping("/{email}")
    public Response delete(@PathVariable("email") String email) {
        Response response = new Response();

        try {
            userService.deleteUser(email);

            response.setResponse("success");
            response.setMessage("회원 삭제를 성공적으로 완료했습니다.");
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("회원 삭제를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

//    @DeleteMapping("/users/{id}")
//    public Response delete(@PathVariable("id") Long id) {
//        Response response = new Response();
//
//        try {
//            userService.deleteUserById(id);
//
//            response.setResponse("success");
//            response.setMessage("회원 삭제를 성공적으로 완료했습니다.");
//        }
//        catch (Exception e) {
//            response.setResponse("failed");
//            response.setMessage("회원 삭제를 하는 도중 오류가 발생했습니다.");
//            response.setData(e.toString());
//        }
//
//        return response;
//    }
}
