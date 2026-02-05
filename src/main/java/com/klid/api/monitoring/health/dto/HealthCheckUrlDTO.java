package com.klid.api.monitoring.health.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 헬스체크 URL DTO
 */
@Getter
@Setter
public class HealthCheckUrlDTO {
    private int seqNo;
    private String url;
    private int instCd;
    private String instNm;
    private String instCenterNm;
    private String updtime;
    private int useYn;
    private int lastRes;
    private int moisYn;
    private int resCd;
    private String errTime;
    private String resNm;
    private int checkYn;
    private int checkSidoYn;
    private String parentName;
}
