package com.klid.api.board.qna.dto;

import lombok.Data;

@Data
public class QnAListItemDTO {
    private String bultnNo;
    private String bultnTitle;
    private String regDate;
    private String isSecret;
    private String userId;
}
