package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageGroupCreateRequest {
    private String pageGroupName;
    private Long menuPageNo;
    private String siteName;
    private String isWebuse;
}
