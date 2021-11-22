package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseInsertDto {
    private String description;
    private int capacity;
    private long chattingRoomId;
    private String location;
    private String name;
}
