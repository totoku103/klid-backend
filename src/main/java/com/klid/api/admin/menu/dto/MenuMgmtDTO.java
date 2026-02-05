package com.klid.api.admin.menu.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 메뉴 관리 DTO
 */
@Getter
@Setter
public class MenuMgmtDTO implements Serializable {
    private String menuNo;
    private String menuName;
    private String menuKind;
    private String menuAuth;
    private String menuAuthNM;
    private String menuPageNo;
    private String menuPageGrpNo;
    private String guid;
    private String siteName;
    private String visibleOrder;
    private String isWebuse;
    private String isFileuse;
    private String webIconClass;
}
