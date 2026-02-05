package com.klid.api.board.notice.dto;

import lombok.Data;

@Data
public class NoticeSurveyChartDTO {
    private Long questionNo;
    private String questionText;
    private String answerText;
    private Integer answerCount;
    private Double percentage;
}
