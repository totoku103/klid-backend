package com.klid.api.system.code.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 관리 DTO
 */
@Getter
@Setter
public class BoardMgmtDTO {
    private String menuName;
    private String guid;
    private String fileExt;
    private Long fileSize;
    private String label;
    private String value;
}
