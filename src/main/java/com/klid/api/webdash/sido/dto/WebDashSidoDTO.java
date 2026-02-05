package com.klid.api.webdash.sido.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebDashSidoDTO {
    // Notice List fields
    private String title;
    private String ymd;

    // Forgery Check fields
    private String instCd;
    private Long cnt;
    private String instNm;

    // Process fields
    private Long totalCnt;
    private Long processCnt;
    private Long completeCnt;
    private Long rnum;
    private Long dataCnt;

    // Region Status Manual fields
    private Long receiptCnt;
}
