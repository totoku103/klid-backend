package com.klid.api.webdash.center.dto;

import com.klid.api.webdash.admin.dto.InciCntDTO;
import lombok.Data;

import java.util.List;

@Data
public class WebDashCenterDTO {
    private String nationNm;
    private int nationCd;
    private int attCnt;
    private String regHh;
    private String sumJson;
    private String dayType;
    private List<InciCntDTO> jsonList;
}
