package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuSaveRequest {
    private List<MenuSaveItem> list;
    private String siteName;

    @Getter
    @Setter
    public static class MenuSaveItem {
        private String menuName;
        private String menuAuth;
        private Long visibleOrder;
        private String isWebuse;
        private Long menuNo;
    }
}
