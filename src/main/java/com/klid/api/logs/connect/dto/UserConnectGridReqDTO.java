package com.klid.api.logs.connect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConnectGridReqDTO {
    private String system;
    private String date1;
    private String date2;
    private String inOutType;
    private String userId;
    private String connectIp;
}
