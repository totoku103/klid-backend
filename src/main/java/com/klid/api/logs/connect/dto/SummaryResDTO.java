package com.klid.api.logs.connect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryResDTO {
    private String fromDate;
    private String toDate;
    private int count;
}
