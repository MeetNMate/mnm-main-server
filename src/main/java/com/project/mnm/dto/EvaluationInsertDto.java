package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EvaluationInsertDto {
    private long appraiseeId;
    private long houseId;
    private int score;
    private String content;
}
