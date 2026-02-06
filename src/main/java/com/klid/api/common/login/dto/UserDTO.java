package com.klid.api.common.login.dto;

import lombok.Data;

/**
 * 사용자 정보 DTO
 */
@Data
public class UserDTO {
    private Long seq;
    private String userId;
    private String instCd;
    private String pntInstCd;
    private String instNm;
    private String pntInstNm;
    private String userName;
    private String localCd;
    private String userPwd;
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
    private String roleCtrs;
    private String roleIics;
    private String roleRms;
    private String roleEws;
    private String roleSd;
    private String ncategory;
    private String nrefindex;
    private String lastpwdmodified;
    private Long lastpwdmodifiedtime;
    private String lockYn;
    private String passResetYn;
    private String ipAddr;
    private String otpKey;
    private String inactiveYn;
    private String authGrpNo;
    private String auth;
    private String authMain;
    private String authSub;
    private String roleTbz01;
    private String roleTbz02;
    private String roleTbz03;
    private String roleTbz04;
    private String roleTbz05;
    private String roleTbz06;
    private String roleNot01;
    private String roleNot02;
    private String roleNot03;
    private String roleNot04;
    private String roleNot05;
    private String roleNot06;
    private String roleRes01;
    private String roleRes02;
    private String roleRes03;
    private String roleRes04;
    private String roleRes05;
    private String roleRes06;
    private String roleSha01;
    private String roleSha02;
    private String roleSha03;
    private String roleSha04;
    private String roleSha05;
    private String roleSha06;
    private String roleQna01;
    private String roleQna02;
    private String roleQna03;
    private String roleQna04;
    private String roleQna05;
    private String roleQna06;
    private Integer loginFailCnt;
    private String instLevel;
}
