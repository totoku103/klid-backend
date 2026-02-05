package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위협레벨 단계 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class ThreatLevelResponse {
    private String levelCode;
    private String levelName;
    private String levelDesc;
    private String levelColor;
    private Integer sortOrder;
}
