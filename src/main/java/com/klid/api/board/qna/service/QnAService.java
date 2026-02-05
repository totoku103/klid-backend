package com.klid.api.board.qna.service;

import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.persistence.QnAMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final QnAMapper qnaMapper;

    public List<QnAListItemDTO> getMainQnaList(final Integer listSize, final String sInstCd) {
        return qnaMapper.getMainQnaList(listSize, sInstCd);
    }
}
