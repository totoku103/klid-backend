package com.klid.api.board.takeover.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeoverBoardListDTO {
    private Long boardNo;
    private String title;
    private String status;
    private String writerNm;
    private String writerInstNm;
    private String receiverNm;
    private String receiverInstNm;
    private LocalDateTime regDate;
    private Integer replyCnt;
}
