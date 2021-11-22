package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ChattingInsertDto {
    private Long uid;
    private String message;
    private Timestamp sendAt;
    private Boolean isRequest;
}