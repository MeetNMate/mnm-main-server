package com.project.mnm.dto.socket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SocketSessionInsertDto {
    private Long uid;
    private Long cid;
    private String sid;
}
