package com.klid.api.env.nationip.dto;

import lombok.Data;

@Data
public class NationIPMgmtDTO {
    private String nationCd;
    private String nationNm;
    private String nationNmCnt;
    private String domain;
    private String continental;
    private Integer nationCnt;
    private String regDt;
    private String krNm;
    private String sip;
    private String eip;
}
