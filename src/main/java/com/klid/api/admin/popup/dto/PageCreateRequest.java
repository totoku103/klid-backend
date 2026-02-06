package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageCreateRequest {
    private String pageName;
    private String siteName;
    private String isWebuse;
    private String webIconClass;
}
