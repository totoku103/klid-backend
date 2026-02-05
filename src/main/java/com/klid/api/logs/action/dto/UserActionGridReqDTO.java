package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActionGridReqDTO {
    private String systemType;
    private String date1;
    private String date2;
    private String actionType;
    private String userName;
    private String userId;
}
