package com.klid.api.system.code.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 공휴일 등록 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class WeekDayRequest {
    private String weekdayDate;
    private String weekdayName;
    private String weekdayType;
    private String useYn;
}
