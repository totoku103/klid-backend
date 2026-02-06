package com.klid.api.env.usermgmthist.dto;

import lombok.Data;

@Data
public class HistoryGridResDTO {
    private Integer commUserRequestSeq;
    private Integer latestCommUserRequestSeq;
    private Integer originUserSeq;
    private String originUserInstCode;
    private String originUserInstName;
    private String originUserId;
    private String originUserName;
    private Integer requestUserSeq;
    private String requestUserName;
    private String requestUserInstCode;
    private String requestUserInstName;
    private String requestType;
    private String requestReason;
    private String requestRegDt;
    private String processState;
    private Integer parentSeq;
    private Integer approveUserSeq;
    private String approveUserName;
    private String approveUserInstCode;
    private String approveUserInstName;
    private String approveReason;
    private String approveRegDt;
}
