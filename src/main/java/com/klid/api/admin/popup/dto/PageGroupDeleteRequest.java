package com.klid.api.admin.popup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageGroupDeleteRequest {
    private Long menuNo;
    private Long menuPageNo;
    private String siteName;
}
