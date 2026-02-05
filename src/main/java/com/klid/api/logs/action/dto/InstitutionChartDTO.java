package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class InstitutionChartDTO {
    private List<Integer> chartXAxis;
    private Map<String, List<InstitutionChartYAxisDTO>> chartData;
}
