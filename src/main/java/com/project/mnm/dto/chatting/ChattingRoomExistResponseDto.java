package com.project.mnm.dto.chatting;

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
