package com.project.mnm.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthLoginResponseDto {
    private Long uid;
    private String token;
}
