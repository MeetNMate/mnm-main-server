package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HouseRuleInsertDto {
    private long houseId;
    private long userId;
    private String rule;
    private int upperNum;
    private int lowerNum;
}
