package com.klid.api.env.instip.dto;

import lombok.Data;

@Data
public class InstIPMgmtDTO {
    private Long no;
    private Long seq;
    private String instCd;
    private String instNm;
    private String pntSInstCd;
    private String pntSInstCdNm;
    private String instNmStr;
    private String sip;
    private String eip;
    private String ipCd;
    private String usrId;
    private String regDt;
    private String usrIp;
    private String ipCont;
}
