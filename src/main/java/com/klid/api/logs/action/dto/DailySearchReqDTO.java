package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DailySearchReqDTO {
    private String systemType;
    private String yyyyMMdd;
}
