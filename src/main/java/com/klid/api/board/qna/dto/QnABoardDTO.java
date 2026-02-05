package com.klid.api.board.qna.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QnABoardDTO {
    private Long boardNo;
    private String boardType;
    private String title;
    private String content;
    private String writerNm;
    private String writerId;
    private String writerInstCd;
    private String writerInstNm;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Integer readCnt;
    private String delYn;
    private String fileGroupNo;
    private Integer commentCnt;
}
