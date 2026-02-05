package com.klid.api.board.qna.service;

import com.klid.api.board.qna.dto.QnABoardDTO;
import com.klid.api.board.qna.dto.QnACommentDTO;
import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.persistence.QnAMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final QnAMapper qnaMapper;

    public List<QnAListItemDTO> getMainQnaList(final Integer listSize, final String sInstCd) {
        return qnaMapper.getMainQnaList(listSize, sInstCd);
    }

    public List<QnAListItemDTO> getPostBoardList(final Integer listSize, final String sInstCd) {
        return qnaMapper.getPostBoardList(listSize, sInstCd);
    }

    public List<QnAListItemDTO> getBoardList(
            final Integer pageNo,
            final Integer pageSize,
            final String searchType,
            final String searchText,
            final String sInstCd) {
        return qnaMapper.getBoardList(pageNo, pageSize, searchType, searchText, sInstCd);
    }

    @Transactional
    public QnABoardDTO getBoardContents(final Long boardNo) {
        // Increment read count
        qnaMapper.incrementReadCount(boardNo);
        return qnaMapper.getBoardContents(boardNo);
    }

    @Transactional
    public Long addBoard(final QnABoardDTO boardDTO) {
        qnaMapper.addBoard(boardDTO);
        return boardDTO.getBoardNo();
    }

    @Transactional
    public void editBoard(final Long boardNo, final QnABoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        qnaMapper.editBoard(boardDTO);
    }

    @Transactional
    public void delBoard(final Long boardNo) {
        qnaMapper.delBoard(boardNo);
    }

    @Transactional
    public Long addComment(final Long boardNo, final QnACommentDTO commentDTO) {
        commentDTO.setBoardNo(boardNo);
        qnaMapper.addComment(commentDTO);
        return commentDTO.getCommentNo();
    }

    public Map<String, Object> checkAuth(final String sInstCd, final String userId) {
        return qnaMapper.checkAuth(sInstCd, userId);
    }
}
