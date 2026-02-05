package com.klid.api.dashboard.dto;

import lombok.Data;

@Data
public class ThreatStatusDTO {
    private String nowThreat;
    private String pastThreat;
    private String pastNm;
    private String nowNm;
    private String instCd;
    private String modDt;
}
