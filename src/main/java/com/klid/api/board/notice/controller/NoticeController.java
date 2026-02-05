package com.klid.api.board.notice.controller;

import com.klid.api.board.notice.dto.*;
import com.klid.api.board.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/main-list")
    public ResponseEntity<List<NoticeListItemDTO>> getMainNoticeList(
            @RequestParam(required = false) final Integer listSize,
            @RequestParam(required = false) final String sAuthMain,
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String sPntInstCd) {
        final List<NoticeListItemDTO> result = noticeService.getMainNoticeList(
                listSize, sAuthMain, sInstCd, sPntInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/post-list")
    public ResponseEntity<List<NoticeListItemDTO>> getPostBoardList(
            @RequestParam(required = false) final Integer listSize,
            @RequestParam(required = false) final String sInstCd) {
        final List<NoticeListItemDTO> result = noticeService.getPostBoardList(listSize, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<NoticeListItemDTO>> getBoardList(
            @RequestParam(required = false) final Integer pageNo,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final String boardType,
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText,
            @RequestParam(required = false) final String sInstCd) {
        final List<NoticeListItemDTO> result = noticeService.getBoardList(
                pageNo, pageSize, boardType, searchType, searchText, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<NoticeBoardDTO> getBoardContents(@PathVariable final Long boardNo) {
        final NoticeBoardDTO result = noticeService.getBoardContents(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Long> addBoard(@RequestBody final NoticeBoardDTO boardDTO) {
        final Long boardNo = noticeService.addBoard(boardDTO);
        return ResponseEntity.ok(boardNo);
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> editBoard(
            @PathVariable final Long boardNo,
            @RequestBody final NoticeBoardDTO boardDTO) {
        noticeService.editBoard(boardNo, boardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> delBoard(@PathVariable final Long boardNo) {
        noticeService.delBoard(boardNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/board-types")
    public ResponseEntity<List<Map<String, Object>>> getBoardTypeList() {
        final List<Map<String, Object>> result = noticeService.getBoardTypeList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, Object>> checkAuth(
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String userId) {
        final Map<String, Object> result = noticeService.checkAuth(sInstCd, userId);
        return ResponseEntity.ok(result);
    }

    // Survey related endpoints
    @PostMapping("/{boardNo}/survey")
    public ResponseEntity<Void> addNoticeSurvey(
            @PathVariable final Long boardNo,
            @RequestBody final NoticeSurveyDTO surveyDTO) {
        noticeService.addNoticeSurvey(boardNo, surveyDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardNo}/survey/answer-count")
    public ResponseEntity<Integer> getSurveyAnswerCount(@PathVariable final Long boardNo) {
        final Integer count = noticeService.getSurveyAnswerCount(boardNo);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/{boardNo}/survey/chart")
    public ResponseEntity<List<NoticeSurveyChartDTO>> getSurveyChart(@PathVariable final Long boardNo) {
        final List<NoticeSurveyChartDTO> result = noticeService.getSurveyChart(boardNo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}/survey/grid")
    public ResponseEntity<List<NoticeSurveyGridDTO>> getSurveyGrid(@PathVariable final Long boardNo) {
        final List<NoticeSurveyGridDTO> result = noticeService.getSurveyGrid(boardNo);
        return ResponseEntity.ok(result);
    }

    // Notice confirmation related endpoints
    @PostMapping("/{boardNo}/confirm")
    public ResponseEntity<Void> addNoticeConfirm(
            @PathVariable final Long boardNo,
            @RequestBody final NoticeConfirmDTO confirmDTO) {
        noticeService.addNoticeConfirm(boardNo, confirmDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardNo}/confirm")
    public ResponseEntity<Void> editNoticeConfirm(
            @PathVariable final Long boardNo,
            @RequestBody final NoticeConfirmDTO confirmDTO) {
        noticeService.editNoticeConfirm(boardNo, confirmDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardNo}/confirm/list")
    public ResponseEntity<List<NoticeConfirmDTO>> getConfirmList(@PathVariable final Long boardNo) {
        final List<NoticeConfirmDTO> result = noticeService.getConfirmList(boardNo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}/confirm/check")
    public ResponseEntity<Map<String, Object>> getConfirmCheck(
            @PathVariable final Long boardNo,
            @RequestParam(required = false) final String userId) {
        final Map<String, Object> result = noticeService.getConfirmCheck(boardNo, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}/confirm/reply-check")
    public ResponseEntity<Map<String, Object>> getConfirmReplyCheck(
            @PathVariable final Long boardNo,
            @RequestParam(required = false) final String userId) {
        final Map<String, Object> result = noticeService.getConfirmReplyCheck(boardNo, userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{boardNo}/confirm")
    public ResponseEntity<Void> delNoticeConfirm(
            @PathVariable final Long boardNo,
            @RequestParam(required = false) final String userId) {
        noticeService.delNoticeConfirm(boardNo, userId);
        return ResponseEntity.ok().build();
    }
}
