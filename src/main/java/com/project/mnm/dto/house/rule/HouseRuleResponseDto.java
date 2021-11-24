package com.project.mnm.dto.house.rule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class HouseRuleResponseDto {
    private long id;
    private long hid;
    private String originalRule;
    private int upperNum;
    private int lowerNum;
}
