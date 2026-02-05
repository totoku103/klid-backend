package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryResDTO {
    private String fromDate;
    private String toDate;
    private int count;
}
