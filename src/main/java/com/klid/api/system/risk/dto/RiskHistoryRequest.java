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
    private String historyDate;
    private String riskLevel;
    private String riskDesc;
    private String regUser;
}
