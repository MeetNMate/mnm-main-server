package com.project.mnm.domain.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLoginUser {
    private String email;
    private String password;

    public RequestLoginUser(String username, String password) {
        this.email = username;
        this.password = password;
    }
}