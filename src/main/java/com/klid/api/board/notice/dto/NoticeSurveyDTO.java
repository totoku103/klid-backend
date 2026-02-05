package com.klid.api.board.notice.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoticeSurveyDTO {
    private Long boardNo;
    private String userId;
    private List<SurveyAnswerDTO> answers;

    @Data
    public static class SurveyAnswerDTO {
        private Long questionNo;
        private String answerType;
        private String answerText;
    }
}
