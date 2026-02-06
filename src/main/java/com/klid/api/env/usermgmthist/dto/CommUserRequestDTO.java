package com.klid.api.env.usermgmthist.dto;

import lombok.Data;

@Data
public class CommUserRequestDTO {
    private Integer seq;
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
    private String lastpwdmodified;
    private String authMain;
    private String authSub;
    private Integer loginFailCnt;
    private String passResetYn;
    private String lockYn;
    private String ipAddr;
    private String otpKey;
    private String gpkiSerialNo;
    private String inactiveYn;
    private Integer requestUserSeq;
    private String requestType;
    private String requestReason;
    private String requestRegDt;
    private String requestInstCd;
    private String processState;
    private Integer parentSeq;
    private Integer approveUserSeq;
    private String approveReason;
    private String approveRegDt;
    private String approveInstCd;
}
