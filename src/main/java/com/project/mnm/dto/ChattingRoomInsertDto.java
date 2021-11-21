package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChattingRoomInsertDto {
    private Long senderUid;
    private Long receiverUid;
}
