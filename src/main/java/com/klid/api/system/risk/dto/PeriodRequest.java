package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 기간 수정 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class PeriodRequest {
    // XML 매퍼 필드
    private Integer isPeriod;
    private String period1;
    private String period2;
    private String period3;
    private String instCd;

    // 기존 필드 (호환성)
    private String periodType;
    private String startDate;
    private String endDate;
}
