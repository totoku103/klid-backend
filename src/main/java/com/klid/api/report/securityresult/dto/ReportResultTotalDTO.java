package com.klid.api.report.securityresult.dto;

import lombok.Data;

@Data
public class ReportResultTotalDTO {
    private Integer nisCnt;
    private Integer cntCnt;
    private Integer tngCnt;
    private Integer totCnt;
    private Integer wormCnt;
    private Integer webCnt;
}
