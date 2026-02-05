package com.klid.api.board.notice.service;

import com.klid.api.board.notice.dto.*;
import com.klid.api.board.notice.persistence.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public List<NoticeListItemDTO> getMainNoticeList(
            final Integer listSize,
            final String sAuthMain,
            final String sInstCd,
            final String sPntInstCd) {
        return noticeMapper.getMainNoticeList(listSize, sAuthMain, sInstCd, sPntInstCd);
    }

    public List<NoticeListItemDTO> getPostBoardList(
            final Integer listSize,
            final String sInstCd) {
        return noticeMapper.getPostBoardList(listSize, sInstCd);
    }

    public List<NoticeListItemDTO> getBoardList(
            final Integer pageNo,
            final Integer pageSize,
            final String boardType,
            final String searchType,
            final String searchText,
            final String sInstCd) {
        return noticeMapper.getBoardList(pageNo, pageSize, boardType, searchType, searchText, sInstCd);
    }

    @Transactional
    public NoticeBoardDTO getBoardContents(final Long boardNo) {
        // Increment read count
        noticeMapper.incrementReadCount(boardNo);
        return noticeMapper.getBoardContents(boardNo);
    }

    @Transactional
    public Long addBoard(final NoticeBoardDTO boardDTO) {
        noticeMapper.addBoard(boardDTO);
        return boardDTO.getBoardNo();
    }

    @Transactional
    public void editBoard(final Long boardNo, final NoticeBoardDTO boardDTO) {
        boardDTO.setBoardNo(boardNo);
        noticeMapper.editBoard(boardDTO);
    }

    @Transactional
    public void delBoard(final Long boardNo) {
        noticeMapper.delBoard(boardNo);
    }

    public List<Map<String, Object>> getBoardTypeList() {
        return noticeMapper.getBoardTypeList();
    }

    public Map<String, Object> checkAuth(final String sInstCd, final String userId) {
        return noticeMapper.checkAuth(sInstCd, userId);
    }

    // Survey methods
    @Transactional
    public void addNoticeSurvey(final Long boardNo, final NoticeSurveyDTO surveyDTO) {
        surveyDTO.setBoardNo(boardNo);
        noticeMapper.addNoticeSurvey(surveyDTO);
    }

    public Integer getSurveyAnswerCount(final Long boardNo) {
        return noticeMapper.getSurveyAnswerCount(boardNo);
    }

    public List<NoticeSurveyChartDTO> getSurveyChart(final Long boardNo) {
        return noticeMapper.getSurveyChart(boardNo);
    }

    public List<NoticeSurveyGridDTO> getSurveyGrid(final Long boardNo) {
        return noticeMapper.getSurveyGrid(boardNo);
    }

    // Confirmation methods
    @Transactional
    public void addNoticeConfirm(final Long boardNo, final NoticeConfirmDTO confirmDTO) {
        confirmDTO.setBoardNo(boardNo);
        noticeMapper.addNoticeConfirm(confirmDTO);
    }

    @Transactional
    public void editNoticeConfirm(final Long boardNo, final NoticeConfirmDTO confirmDTO) {
        confirmDTO.setBoardNo(boardNo);
        noticeMapper.editNoticeConfirm(confirmDTO);
    }

    public List<NoticeConfirmDTO> getConfirmList(final Long boardNo) {
        return noticeMapper.getConfirmList(boardNo);
    }

    public Map<String, Object> getConfirmCheck(final Long boardNo, final String userId) {
        return noticeMapper.getConfirmCheck(boardNo, userId);
    }

    public Map<String, Object> getConfirmReplyCheck(final Long boardNo, final String userId) {
        return noticeMapper.getConfirmReplyCheck(boardNo, userId);
    }

    @Transactional
    public void delNoticeConfirm(final Long boardNo, final String userId) {
        noticeMapper.delNoticeConfirm(boardNo, userId);
    }
}
