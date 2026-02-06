package com.klid.api.common.file.dto;

import lombok.Data;

/**
 * 사고 첨부 파일 정보 DTO
 */
@Data
public class AccFileInfoDTO {
    private Long attSeq;
    private String inciNo;
    private String clf;
    private String athPath;
    private String athFileNm;
    private String athCntTyp;
    private String realName;
    private String savePath;
    private String fileName;
    private String fileType;
    private String originalFileName;
}
