package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class HouseRoleUpdateDto {
    private long userId;
    private String role;
    private int week;
    private int routine;
}
