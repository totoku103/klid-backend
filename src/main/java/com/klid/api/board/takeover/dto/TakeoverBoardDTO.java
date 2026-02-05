package com.klid.api.board.takeover.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TakeoverBoardDTO {
    private Long boardNo;
    private String title;
    private String content;
    private String status; // 진행상태: 진행중, 확인, 종결
    private String writerNm;
    private String writerId;
    private String writerInstCd;
    private String writerInstNm;
    private String receiverNm;
    private String receiverId;
    private String receiverInstCd;
    private String receiverInstNm;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private LocalDateTime confirmDate;
    private LocalDateTime finishDate;
    private String delYn;
    private String fileGroupNo;
}
