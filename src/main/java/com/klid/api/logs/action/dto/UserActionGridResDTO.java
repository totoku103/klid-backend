package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActionGridResDTO {
    private String seq;
    private String actionType;
    private String actionTypeMessage;
    private String parentMenuName;
    private String menuName;
    private String fullMenuName;
    private String userName;
    private String userId;
    private String institutionName;
    private Long regDate;
    private String regDateHumanFormat;
    private String connectIp;
    private String systemType;
    private String reason;
}
