package com.klid.api.logs.connect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionChartYAxisDTO {
    private Integer ymd;
    private String institutionName;
    private String institutionCode;
    private int totalCount;
}
