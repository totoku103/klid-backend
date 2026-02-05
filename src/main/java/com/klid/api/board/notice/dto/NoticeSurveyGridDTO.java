package com.klid.api.board.notice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeSurveyGridDTO {
    private Long answerNo;
    private Long questionNo;
    private String questionText;
    private String answerText;
    private String userNm;
    private String userInstNm;
    private LocalDateTime regDate;
}
