package com.klid.api.logs.connect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConnectGridResDTO {
    private Long ymdhms;
    private String ymdhmsHuman;
    private String inOutType;
    private String inOutTypeMessage;
    private String instNm;
    private String userName;
    private String userId;
    private String connectIp;
    private String system;
}
