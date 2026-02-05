package com.klid.api.logs.connect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryCounterResDTO {
    private SummaryResDTO ctrsPrevDay;
    private SummaryResDTO ctrsPrevWeek;
    private SummaryResDTO ctrsPrevMonth;
    private SummaryResDTO otherPrevDay;
    private SummaryResDTO otherPrevWeek;
    private SummaryResDTO otherPrevMonth;
}
