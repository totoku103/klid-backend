package com.klid.api.report.collection.dto;

import lombok.Data;

@Data
public class ReportHackingDTO {
    private String seqNo;
    private String inciNo;
    private String dmgInstNm;
    private String hackingDt;
    private String hackingMonth;
    private String hackingDay;
    private String regDt;
    private String inciTarget;
    private String ipAddress;
    private String instType;
    private String domainNm;
    private String netDiv;
    private String hackingCont;
    private String attackTypeNm;
    private String remark;
    private String mediaReport;
    private String inciHistory;
    private String analysisHistory;
    private String inciPrcsStatNm;
}
