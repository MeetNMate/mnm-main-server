package com.project.mnm.dto.house.lobby;

import com.project.mnm.dto.house.common.UserResponseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class HouseResponseDto {
    private long id;
    private String name;
    private String description;
    private String location;
    private List<UserResponseDto> users;
}
