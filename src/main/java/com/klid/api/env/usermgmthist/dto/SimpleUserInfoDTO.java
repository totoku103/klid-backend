package com.klid.api.env.usermgmthist.dto;

import lombok.Data;

@Data
public class SimpleUserInfoDTO {
    private Integer seq;
    private Integer commUserSeq;
    private String instCd;
    private String instNm;
    private String userId;
    private String userName;
    private String userPwd;
    private String moblPhnNo;
    private String offcTelNo;
    private String emailAddr;
    private String smsYn;
    private String useYn;
    private String authMain;
    private String authMainName;
    private String authSub;
    private String authSubName;
    private String passResetYn;
    private String lockYn;
    private String ipAddr;
    private String otpKey;
    private String gpkiSerialNo;
    private String inactiveYn;
}
