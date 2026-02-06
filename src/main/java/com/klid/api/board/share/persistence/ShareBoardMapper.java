package com.klid.api.board.share.persistence;

import com.klid.api.board.share.dto.ShareBoardDTO;
import com.klid.api.board.share.dto.ShareBoardListDTO;
import com.klid.api.board.share.dto.ShareBoardSidoCntDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShareBoardMapper {

    List<ShareBoardListDTO> getBoardList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("searchType") String searchType,
            @Param("searchText") String searchText,
            @Param("sInstCd") String sInstCd);

    ShareBoardDTO getBoardContents(@Param("boardNo") Long boardNo);

    void incrementReadCount(@Param("boardNo") Long boardNo);

    void addBoard(ShareBoardDTO boardDTO);

    void editBoard(ShareBoardDTO boardDTO);

    void delBoard(@Param("boardNo") Long boardNo);

    List<ShareBoardSidoCntDTO> getShareBoardSidoCnt(
            @Param("searchType") String searchType,
            @Param("searchText") String searchText);

    Map<String, Object> checkAuth(
            @Param("sInstCd") String sInstCd,
            @Param("userId") String userId);
}
