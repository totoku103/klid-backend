package com.klid.api.monitoring.dto;

import lombok.Data;

@Data
public class MonitoringDetailDTO {
    private String instNm;
    private String instCd;
    private String localCd;
    private String pntInstCd;
    private Integer hmCnt;
    private Integer foCnt;
}
