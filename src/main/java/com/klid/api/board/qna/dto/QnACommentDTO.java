package com.klid.api.board.qna.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QnACommentDTO {
    private Long commentNo;
    private Long boardNo;
    private String content;
    private String writerNm;
    private String writerId;
    private String writerInstCd;
    private String writerInstNm;
    private LocalDateTime regDate;
    private String delYn;
}
