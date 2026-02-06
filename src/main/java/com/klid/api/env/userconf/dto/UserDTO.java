package com.klid.api.env.userconf.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer seq;
    private String userId;
    private String instCd;
    private String userName;
    private String password;
    private String grade;
    private String moblPhnNo;
    private String homeTelNo;
    private String offcTelNo;
    private String offcFaxNo;
    private String emailAddr;
    private String smsYn;
    private String emailYn;
    private String useYn;
    private String centerUserYn;
    private String pkiDn;
    private String regDt;
    private String instNm;
    private String authMain;
    private String authSub;
    private String passResetYn;
    private String lockYn;
    private String ipAddr;
    private String lastpwdmodified;
    private String otpKey;
    private String gpkiSerialNo;
    private String inactiveYn;
    private String roleSd;
}
