package com.klid.api.board.share.service;

import com.klid.api.board.share.dto.ShareBoardDTO;
import com.klid.api.board.share.dto.ShareBoardListDTO;
import com.klid.api.board.share.dto.ShareBoardSidoCntDTO;
import com.klid.api.board.share.persistence.ShareBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("apiShareBoardService")
@RequiredArgsConstructor
public class ShareBoardService {

    private final ShareBoardMapper shareBoardMapper;

    public List<ShareBoardListDTO> getBoardList(
            final Integer pageNo,
            final Integer pageSize,
            final String searchType,
            final String searchText,
            final String sInstCd) {
        return shareBoardMapper.getBoardList(pageNo, pageSize, searchType, searchText, sInstCd);
    }

    @Transactional
    public ShareBoardDTO getBoardContents(final Long boardNo) {
        // Increment read count
        shareBoardMapper.incrementReadCount(boardNo);
        return shareBoardMapper.getBoardContents(boardNo);
    }

    @Transactional
    public Long addBoard(final ShareBoardDTO boardDTO) {
        shareBoardMapper.addBoard(boardDTO);
        return boardDTO.getBoardNo();
    }

    @Transactional
    public void editBoard(final Long boardNo, final ShareBoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        shareBoardMapper.editBoard(boardDTO);
    }

    @Transactional
    public void delBoard(final Long boardNo) {
        shareBoardMapper.delBoard(boardNo);
    }

    public List<ShareBoardSidoCntDTO> getShareBoardSidoCnt(
            final String searchType,
            final String searchText) {
        return shareBoardMapper.getShareBoardSidoCnt(searchType, searchText);
    }

    public Map<String, Object> checkAuth(final String sInstCd, final String userId) {
        return shareBoardMapper.checkAuth(sInstCd, userId);
    }
}
