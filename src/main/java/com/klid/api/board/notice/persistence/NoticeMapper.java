package com.klid.api.board.notice.persistence;

import com.klid.api.board.notice.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface NoticeMapper {

    List<NoticeListItemDTO> getMainNoticeList(
            @Param("listSize") Integer listSize,
            @Param("sAuthMain") String sAuthMain,
            @Param("sInstCd") String sInstCd,
            @Param("sPntInstCd") String sPntInstCd
    );

    List<NoticeListItemDTO> getPostBoardList(
            @Param("listSize") Integer listSize,
            @Param("sInstCd") String sInstCd);

    List<NoticeListItemDTO> getBoardList(
            @Param("pageNo") Integer pageNo,
            @Param("pageSize") Integer pageSize,
            @Param("boardType") String boardType,
            @Param("searchType") String searchType,
            @Param("searchText") String searchText,
            @Param("sInstCd") String sInstCd);

    NoticeBoardDTO getBoardContents(@Param("boardNo") Long boardNo);

    void incrementReadCount(@Param("boardNo") Long boardNo);

    void addBoard(NoticeBoardDTO boardDTO);

    void editBoard(NoticeBoardDTO boardDTO);

    void delBoard(@Param("boardNo") Long boardNo);

    List<Map<String, Object>> getBoardTypeList();

    Map<String, Object> checkAuth(
            @Param("sInstCd") String sInstCd,
            @Param("userId") String userId);

    // Survey methods
    void addNoticeSurvey(NoticeSurveyDTO surveyDTO);

    Integer getSurveyAnswerCount(@Param("boardNo") Long boardNo);

    List<NoticeSurveyChartDTO> getSurveyChart(@Param("boardNo") Long boardNo);

    List<NoticeSurveyGridDTO> getSurveyGrid(@Param("boardNo") Long boardNo);

    // Confirmation methods
    void addNoticeConfirm(NoticeConfirmDTO confirmDTO);

    void editNoticeConfirm(NoticeConfirmDTO confirmDTO);

    List<NoticeConfirmDTO> getConfirmList(@Param("boardNo") Long boardNo);

    Map<String, Object> getConfirmCheck(
            @Param("boardNo") Long boardNo,
            @Param("userId") String userId);

    Map<String, Object> getConfirmReplyCheck(
            @Param("boardNo") Long boardNo,
            @Param("userId") String userId);

    void delNoticeConfirm(
            @Param("boardNo") Long boardNo,
            @Param("userId") String userId);
}
