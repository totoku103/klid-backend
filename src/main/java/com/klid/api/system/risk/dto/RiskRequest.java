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
    private String riskId;
    private String riskLevel;
    private String riskDesc;
    private String useYn;
}
