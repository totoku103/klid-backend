package com.klid.api.board.resource.service;

import com.klid.api.board.resource.dto.ResourceBoardDTO;
import com.klid.api.board.resource.dto.ResourceBoardListDTO;
import com.klid.api.board.resource.persistence.ResourceBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResourceBoardService {

    private final ResourceBoardMapper resourceBoardMapper;

    public List<ResourceBoardListDTO> getBoardList(
            final Integer pageNo,
            final Integer pageSize,
            final String searchType,
            final String searchText,
            final String sInstCd) {
        return resourceBoardMapper.getBoardList(pageNo, pageSize, searchType, searchText, sInstCd);
    }

    @Transactional
    public ResourceBoardDTO getBoardContents(final Long boardNo) {
        // Increment read count
        resourceBoardMapper.incrementReadCount(boardNo);
        return resourceBoardMapper.getBoardContents(boardNo);
    }

    @Transactional
    public Long addBoard(final ResourceBoardDTO boardDTO) {
        resourceBoardMapper.addBoard(boardDTO);
        return boardDTO.getBoardNo();
    }

    @Transactional
    public void editBoard(final Long boardNo, final ResourceBoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        resourceBoardMapper.editBoard(boardDTO);
    }

    @Transactional
    public void delBoard(final Long boardNo) {
        resourceBoardMapper.delBoard(boardNo);
    }

    public Map<String, Object> checkAuth(final String sInstCd, final String userId) {
        return resourceBoardMapper.checkAuth(sInstCd, userId);
    }

    // MOIS (행안부) Board Methods
    public List<ResourceBoardListDTO> getMoisBoardList(
            final Integer pageNo,
            final Integer pageSize,
            final String searchType,
            final String searchText) {
        return resourceBoardMapper.getMoisBoardList(pageNo, pageSize, searchType, searchText);
    }

    @Transactional
    public ResourceBoardDTO getMoisBoardContents(final Long boardNo) {
        // Increment read count
        resourceBoardMapper.incrementMoisReadCount(boardNo);
        return resourceBoardMapper.getMoisBoardContents(boardNo);
    }

    @Transactional
    public Long addMoisBoard(final ResourceBoardDTO boardDTO) {
        resourceBoardMapper.addMoisBoard(boardDTO);
        return boardDTO.getBoardNo();
    }

    @Transactional
    public void editMoisBoard(final Long boardNo, final ResourceBoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        resourceBoardMapper.editMoisBoard(boardDTO);
    }

    @Transactional
    public void delMoisBoard(final Long boardNo) {
        resourceBoardMapper.delMoisBoard(boardNo);
    }
}
