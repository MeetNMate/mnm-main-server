package com.project.mnm.dto;

import com.project.mnm.domain.Profile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseRoleResponseDto {
    private long id;
    private String role;
    private String userName;
}
