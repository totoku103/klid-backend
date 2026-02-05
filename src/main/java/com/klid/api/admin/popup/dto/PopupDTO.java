package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopupDTO {
    // Page fields
    private Long menuNo;
    private String menuName;
    private String menuKind;
    private String siteName;
    private String isWebuse;
    private String webIconClass;
    private String pageName;
    private Long visibleOrder;

    // Page Group fields
    private String pageGroupName;
    private Long menuPageNo;
    private String menuAuth;

    // Menu fields
    private Long menuPageGrpNo;
    private String guid;
    private String menuNm;
    private Integer addYn;
}
