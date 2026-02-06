package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageGroupSaveRequest {
    private List<PageGroupSaveItem> list;
    private String siteName;

    @Getter
    @Setter
    public static class PageGroupSaveItem {
        private String menuName;
        private Long visibleOrder;
        private String menuAuth;
        private String isWebuse;
        private Long menuNo;
        private Long menuPageNo;
    }
}
