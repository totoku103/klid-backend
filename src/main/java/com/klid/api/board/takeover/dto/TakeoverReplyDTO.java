package com.klid.api.board.takeover.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeoverReplyDTO {
    private Long replyNo;
    private Long boardNo;
    private String content;
    private String writerNm;
    private String writerId;
    private String writerInstCd;
    private LocalDateTime regDate;
    private String delYn;
}
