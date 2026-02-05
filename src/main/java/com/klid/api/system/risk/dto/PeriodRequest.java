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
    private String periodType;
    private String startDate;
    private String endDate;
}
