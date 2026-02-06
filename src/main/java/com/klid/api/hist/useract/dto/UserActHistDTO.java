package com.klid.api.hist.useract.dto;

import lombok.Data;

@Data
public class UserActHistDTO {
    private Integer seq;
    private String guid;
    private String actType;
    private String refTable;
    private String regUserId;
    private String regUserName;
    private String regDate;
    private String menuName;
    private String parentMenuName;
    private String instNm;
    private String usrIp;
}
