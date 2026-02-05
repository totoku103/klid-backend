package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위협레벨 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class ThreatResponse {
    private String threatId;
    private String threatLevel;
    private String threatLevelName;
    private String threatDesc;
    private String startDate;
    private String endDate;
    private String regDate;
    private String modDate;
}
