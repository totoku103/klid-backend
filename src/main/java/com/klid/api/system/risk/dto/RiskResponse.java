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
    private String riskId;
    private String riskLevel;
    private String riskDesc;
    private String useYn;
    private String regDate;
    private String modDate;
}
