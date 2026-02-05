package com.klid.api.board.takeover.persistence;

import com.klid.api.board.takeover.dto.TakeoverBoardDTO;
import com.klid.api.board.takeover.dto.TakeoverBoardListDTO;
import com.klid.api.board.takeover.dto.TakeoverReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TakeoverBoardMapper {

    List<TakeoverBoardListDTO> getBoardList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("searchType") String searchType,
            @Param("searchText") String searchText,
            @Param("status") String status,
            @Param("sInstCd") String sInstCd);

    TakeoverBoardDTO getBoardInfo(@Param("boardNo") Long boardNo);

    void addBoard(TakeoverBoardDTO boardDTO);

    void editBoard(TakeoverBoardDTO boardDTO);

    void addBoardConfirm(@Param("boardNo") Long boardNo);

    void editBoardFinish(@Param("boardNo") Long boardNo);

    List<TakeoverReplyDTO> getAnsBoardList(@Param("boardNo") Long boardNo);

    void addAnsBoard(TakeoverReplyDTO replyDTO);
}
