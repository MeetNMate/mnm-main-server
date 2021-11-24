package com.project.mnm.dto.house.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class HouseRoleInsertDto {
    private long houseId;
    private long userId;
    private String role;
    private int week;
    private Timestamp startAt;
    private int routine;
}
