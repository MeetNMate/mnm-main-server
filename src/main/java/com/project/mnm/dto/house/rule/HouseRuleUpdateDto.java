package com.project.mnm.dto.house.rule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseRuleUpdateDto {
    private long userId;
    private String rule;
    private int upperNum;
    private int lowerNum;
}
