package com.klid.api.system.risk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 기간 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class PeriodResponse {
    private String periodId;
    private String periodType;
    private String startDate;
    private String endDate;
    private String regDate;
    private String modDate;
}
