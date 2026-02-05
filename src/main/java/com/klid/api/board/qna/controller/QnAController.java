package com.klid.api.board.qna.controller;

import com.klid.api.board.qna.dto.QnABoardDTO;
import com.klid.api.board.qna.dto.QnACommentDTO;
import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/qna")
public class QnAController {

    private final QnAService qnaService;

    @GetMapping("/main-list")
    public ResponseEntity<List<QnAListItemDTO>> getMainQnaList(
            @RequestParam(required = false) final Integer listSize,
            @RequestParam(required = false) final String sInstCd) {
        final List<QnAListItemDTO> result = qnaService.getMainQnaList(listSize, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/post-list")
    public ResponseEntity<List<QnAListItemDTO>> getPostBoardList(
            @RequestParam(required = false) final Integer listSize,
            @RequestParam(required = false) final String sInstCd) {
        final List<QnAListItemDTO> result = qnaService.getPostBoardList(listSize, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<QnAListItemDTO>> getBoardList(
            @RequestParam(required = false) final Integer pageNo,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText,
            @RequestParam(required = false) final String sInstCd) {
        final List<QnAListItemDTO> result = qnaService.getBoardList(
                pageNo, pageSize, searchType, searchText, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<QnABoardDTO> getBoardContents(@PathVariable final Long boardNo) {
        final QnABoardDTO result = qnaService.getBoardContents(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Long> addBoard(@RequestBody final QnABoardDTO boardDTO) {
        final Long boardNo = qnaService.addBoard(boardDTO);
        return ResponseEntity.ok(boardNo);
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> editBoard(
            @PathVariable final Long boardNo,
            @RequestBody final QnABoardDTO boardDTO) {
        qnaService.editBoard(boardNo, boardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> delBoard(@PathVariable final Long boardNo) {
        qnaService.delBoard(boardNo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{boardNo}/comments")
    public ResponseEntity<Long> addComment(
            @PathVariable final Long boardNo,
            @RequestBody final QnACommentDTO commentDTO) {
        final Long commentNo = qnaService.addComment(boardNo, commentDTO);
        return ResponseEntity.ok(commentNo);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, Object>> checkAuth(
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String userId) {
        final Map<String, Object> result = qnaService.checkAuth(sInstCd, userId);
        return ResponseEntity.ok(result);
    }
}
