package com.klid.api.board.share.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShareBoardListDTO {
    private Long boardNo;
    private String boardType;
    private String title;
    private String writerNm;
    private String writerInstNm;
    private LocalDateTime regDate;
    private Integer readCnt;
    private Integer fileCnt;
}
