package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위험도 이력 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class RiskHistoryResponse {
    private String historyId;
    private String historyDate;
    private String riskLevel;
    private String riskDesc;
    private String regUser;
    private String regUserName;
    private String regDate;
}
