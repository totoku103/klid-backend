package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageSaveRequest {
    private List<PageSaveItem> list;
    private String siteName;

    @Getter
    @Setter
    public static class PageSaveItem {
        private String menuName;
        private Long visibleOrder;
        private String isWebuse;
        private String webIconClass;
        private Long menuNo;
    }
}
