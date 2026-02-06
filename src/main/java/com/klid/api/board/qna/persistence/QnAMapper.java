package com.klid.api.board.qna.persistence;

import com.klid.api.board.qna.dto.QnABoardDTO;
import com.klid.api.board.qna.dto.QnACommentDTO;
import com.klid.api.board.qna.dto.QnAListItemDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QnAMapper {

    List<QnAListItemDTO> getMainQnaList(
            @Param("listSize") Integer listSize,
            @Param("sInstCd") String sInstCd
    );

    List<QnAListItemDTO> getPostBoardList(
            @Param("listSize") Integer listSize,
            @Param("sInstCd") String sInstCd);

    List<QnAListItemDTO> getBoardList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("searchType") String searchType,
            @Param("searchText") String searchText,
            @Param("sInstCd") String sInstCd);

    QnABoardDTO getBoardContents(@Param("boardNo") Long boardNo);

    void incrementReadCount(@Param("boardNo") Long boardNo);

    void addBoard(QnABoardDTO boardDTO);

    void editBoard(QnABoardDTO boardDTO);

    void delBoard(@Param("boardNo") Long boardNo);

    void addComment(QnACommentDTO commentDTO);

    Map<String, Object> checkAuth(
            @Param("sInstCd") String sInstCd,
            @Param("userId") String userId);
}
