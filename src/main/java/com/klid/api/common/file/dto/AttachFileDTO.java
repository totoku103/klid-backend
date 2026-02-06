package com.klid.api.common.file.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 첨부 파일 정보 DTO
 */
@Data
public class AttachFileDTO {
    private Long fileNo;
    private Long boardNo;
    private String fileName;
    private String originalFileName;
    private String fileType;
    private Long fileSize;
    private String attachPath;
    private String athPath;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;
    private String memo;
}
