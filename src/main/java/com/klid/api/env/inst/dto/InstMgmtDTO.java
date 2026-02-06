package com.klid.api.env.inst.dto;

import lombok.Data;

@Data
public class InstMgmtDTO {
    private String instCd;
    private String instNm;
    private String pntSInstCd;
    private String pntSInstCdNm;
    private String localCd;
    private String localCdNm;
    private String useYn;
    private String useYnNm;
    private String regDt;
    private Integer instLevel;
    private Integer instOrder;
    private String typeBig;
    private String typeMid;
    private String typeSml;
    private String pmsAssUnitYn;
    private Integer depth;
    private String typeMidNm;
    private String typeSmlNm;
    private String instClf;
    private String agentIp;
    private String posDiv;
}
