package com.project.mnm.controller;

import com.project.mnm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, UserService userService1){
        this.userService = userService1;
    }
}
