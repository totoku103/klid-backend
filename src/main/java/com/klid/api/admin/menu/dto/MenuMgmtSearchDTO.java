package com.klid.api.admin.menu.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 메뉴 관리 조회 조건 DTO
 */
@Getter
@Setter
public class MenuMgmtSearchDTO {
    private String menuPageNo;
    private String menuPageGrpNo;
    private String siteName;
}
