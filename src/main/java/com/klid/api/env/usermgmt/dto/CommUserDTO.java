package com.klid.api.env.usermgmt.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommUserDTO {
    private Integer commUserSeq;
    private String instCd;
    private String userId;
    private String userName;
    private String userPwd;
    private String moblPhnNo;
    private String offcTelNo;
    private String emailAddr;
    private String smsYn;
    private String useYn;
    private String regDt;
    private Timestamp lastPwdModified;
    private String authMain;
    private String authSub;
    private String ipAddr;
    private String gpkiSerialNo;
    private Integer loginFailCnt;
    private String otpKey;
    private String passResetYn;
    private String lockYn;
    private String inactiveYn;
}
