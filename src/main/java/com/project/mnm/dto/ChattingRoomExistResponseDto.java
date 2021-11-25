package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChattingRoomExistResponseDto {
    private boolean isExisted;
    private Long cid;
}
