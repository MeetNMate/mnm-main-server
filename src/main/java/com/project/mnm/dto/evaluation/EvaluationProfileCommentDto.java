package com.project.mnm.dto.evaluation;

import com.project.mnm.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EvaluationProfileCommentDto {
    private long id;
    private Profile appraiser;
    private int score;
    private String content;
    private Timestamp createAt;
}
