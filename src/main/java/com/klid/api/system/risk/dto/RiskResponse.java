package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위험도 조회 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class RiskResponse {
    // 위험 기준 관련
    private String basis1;
    private String basis2;
    private String basis3;
    private String basis4;
    private String basis5;

    // 위협 레벨 관련
    private String levelCd;
    private String levelNm;

    // 위협 현황 관련
    private String pastThreat;
    private String nowThreat;
    private String instCd;
    private String modDt;
    private String pastNm;
    private String nowNm;

    // 기간 관련
    private String period1;
    private String period2;
    private String period3;

    // 위협 내용
    private String threatCont;

    // 이력 관련
    private Integer logSeq;
    private String step;
    private String contents;
    private String usrId;
    private String usrName;
    private String regDt;
    private String usrIp;
}
