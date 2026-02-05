package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위험도 수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class RiskRequest {
    // XML 매퍼 필드
    private String basis;
    private String levelCd;
    private String pastThreat;
    private String nowThreat;
    private String threatCont;
    private String instCd;
    private Integer isPeriod;
    private String period1;
    private String period2;
    private String period3;

    // 이력 관련
    private Integer logSeq;
    private String step;
    private String contents;
    private String usrId;
    private String usrName;
    private String usrIp;

    // 기존 필드 (호환성)
    private String riskId;
    private String riskLevel;
    private String riskDesc;
    private String useYn;
}
