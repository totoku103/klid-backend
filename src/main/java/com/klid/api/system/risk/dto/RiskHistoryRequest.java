package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위험도 이력 등록 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class RiskHistoryRequest {
    // XML 매퍼 필드
    private Integer logSeq;
    private String step;
    private String contents;
    private String usrId;
    private String usrName;
    private String usrIp;

    // 기존 필드 (호환성)
    private String historyDate;
    private String riskLevel;
    private String riskDesc;
    private String regUser;
}
