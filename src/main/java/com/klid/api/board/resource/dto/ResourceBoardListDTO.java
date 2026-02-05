package com.klid.api.board.resource.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResourceBoardListDTO {
    private Long boardNo;
    private String boardType;
    private String title;
    private String writerNm;
    private String writerInstNm;
    private LocalDateTime regDate;
    private Integer readCnt;
    private Integer fileCnt;
}
