package com.klid.api.hist.userinout.dto;

import lombok.Data;

@Data
public class UserInoutHistMgmtDTO {
    private String label;
    private String value;
    private Integer no;
    private String usrId;
    private String usrName;
    private String logDt;
    private String logCd;
    private String remark;
    private String usrIp;
    private String instNm;
}
