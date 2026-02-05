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
    // XML 매퍼 필드
    private String pastThreat;
    private String nowThreat;
    private String threatCont;

    // 기존 필드 (호환성)
    private String threatLevel;
    private String threatDesc;
    private String startDate;
    private String endDate;
}
