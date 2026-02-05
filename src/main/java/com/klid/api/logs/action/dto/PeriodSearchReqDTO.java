package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PeriodSearchReqDTO {
    private String systemType;
    private String startYmd;
    private String endYmd;
}
