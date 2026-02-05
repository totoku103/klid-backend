package com.klid.api.webdash.admin.dto;

import lombok.Data;

@Data
public class TbzledgeCntDTO {
    private String type;
    private int completeCnt;
    private int processCnt;

    public int getTotalCnt() {
        return this.completeCnt + this.processCnt;
    }
}
