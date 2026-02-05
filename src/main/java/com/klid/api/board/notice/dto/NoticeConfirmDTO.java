package com.klid.api.board.notice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeConfirmDTO {
    private Long confirmNo;
    private Long boardNo;
    private String userId;
    private String userNm;
    private String userInstCd;
    private String userInstNm;
    private String confirmContent;
    private LocalDateTime confirmDate;
    private LocalDateTime replyDate;
}
