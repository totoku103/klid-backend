package com.klid.api.monitoring.dto;

import lombok.Data;

@Data
public class MonitoringStatsDTO {
    private Integer healthNormalCnt;
    private Integer healthErrCnt;
    private Integer urlNormalCnt;
    private Integer urlErrCnt;
}
