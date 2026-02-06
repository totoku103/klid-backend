package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuCreateRequest {
    private List<MenuCreateItem> list;
    private Long menuPageNo;
    private Long menuPageGrpNo;
    private String siteName;

    @Getter
    @Setter
    public static class MenuCreateItem {
        private Long menuNo;
        private String menuAuth;
        private Long visibleOrder;
        private String isWebuse;
        private Integer addYn;
        private String menuNm;
        private String guid;
    }
}
