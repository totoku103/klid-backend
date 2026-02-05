package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 위협레벨 수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class ThreatRequest {
    private String threatLevel;
    private String threatDesc;
    private String startDate;
    private String endDate;
}
