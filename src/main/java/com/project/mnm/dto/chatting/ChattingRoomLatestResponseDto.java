package com.project.mnm.dto.chatting;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ChattingRoomLatestResponseDto {
    private Long uid;
    private Timestamp sendAt;
    private String message;
    private int number;
}
