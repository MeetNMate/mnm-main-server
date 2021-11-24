package com.project.mnm.dto.house.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserResponseDto {
    private long id;
    private String image;
    private String name;
}
