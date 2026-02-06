package com.klid.api.report.weeklystate.dto;

import lombok.Data;

@Data
public class ReportDailyDTO {
    private Integer totalCnt;
    private Integer endCnt;
    private Integer ingCnt;
    private String inciTypeNm;
    private Integer inciTypeInst1;
    private Integer inciTypeInst2;
    private Integer inciTypeInst3;
    private Integer inciTypeInst4;
    private Integer inciTypeInst5;
    private Integer sums;
}
