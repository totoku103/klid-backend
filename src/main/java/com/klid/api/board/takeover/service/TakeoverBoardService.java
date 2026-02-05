package com.klid.api.board.takeover.service;

import com.klid.api.board.takeover.dto.TakeoverBoardDTO;
import com.klid.api.board.takeover.dto.TakeoverBoardListDTO;
import com.klid.api.board.takeover.dto.TakeoverReplyDTO;
import com.klid.api.board.takeover.persistence.TakeoverBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TakeoverBoardService {

    private final TakeoverBoardMapper takeoverBoardMapper;

    public List<TakeoverBoardListDTO> getBoardList(
            final Integer pageNo,
            final Integer pageSize,
            final String searchType,
            final String searchText,
            final String status,
            final String sInstCd) {
        return takeoverBoardMapper.getBoardList(pageNo, pageSize, searchType, searchText, status, sInstCd);
    }

    public TakeoverBoardDTO getBoardInfo(final Long boardNo) {
        return takeoverBoardMapper.getBoardInfo(boardNo);
    }

    @Transactional
    public Long addBoard(final TakeoverBoardDTO boardDTO) {
        takeoverBoardMapper.addBoard(boardDTO);
        return boardDTO.getBoardNo();
    }

    @Transactional
    public void editBoard(final Long boardNo, final TakeoverBoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        takeoverBoardMapper.editBoard(boardDTO);
    }

    @Transactional
    public void addBoardConfirm(final Long boardNo) {
        takeoverBoardMapper.addBoardConfirm(boardNo);
    }

    @Transactional
    public void editBoardFinish(final Long boardNo) {
        takeoverBoardMapper.editBoardFinish(boardNo);
    }

    public List<TakeoverReplyDTO> getAnsBoardList(final Long boardNo) {
        return takeoverBoardMapper.getAnsBoardList(boardNo);
    }

    @Transactional
    public Long addAnsBoard(final Long boardNo, final TakeoverReplyDTO replyDTO) {
        replyDTO.setBoardNo(boardNo);
        takeoverBoardMapper.addAnsBoard(replyDTO);
        return replyDTO.getReplyNo();
    }
}
