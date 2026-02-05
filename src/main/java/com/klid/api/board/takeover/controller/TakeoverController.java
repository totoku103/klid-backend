package com.klid.api.board.takeover.controller;

import com.klid.api.board.takeover.dto.TakeoverBoardDTO;
import com.klid.api.board.takeover.dto.TakeoverBoardListDTO;
import com.klid.api.board.takeover.dto.TakeoverReplyDTO;
import com.klid.api.board.takeover.service.TakeoverBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/takeover")
public class TakeoverController {

    private final TakeoverBoardService takeoverBoardService;

    @GetMapping("/list")
    public ResponseEntity<List<TakeoverBoardListDTO>> getBoardList(
            @RequestParam(required = false) final Integer pageNo,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText,
            @RequestParam(required = false) final String status,
            @RequestParam(required = false) final String sInstCd) {
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(
                pageNo, pageSize, searchType, searchText, status, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<TakeoverBoardDTO> getBoardInfo(@PathVariable final Long boardNo) {
        final TakeoverBoardDTO result = takeoverBoardService.getBoardInfo(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Long> addBoard(@RequestBody final TakeoverBoardDTO boardDTO) {
        final Long boardNo = takeoverBoardService.addBoard(boardDTO);
        return ResponseEntity.ok(boardNo);
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> editBoard(
            @PathVariable final Long boardNo,
            @RequestBody final TakeoverBoardDTO boardDTO) {
        takeoverBoardService.editBoard(boardNo, boardDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardNo}/confirm")
    public ResponseEntity<Void> addBoardConfirm(@PathVariable final Long boardNo) {
        takeoverBoardService.addBoardConfirm(boardNo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardNo}/finish")
    public ResponseEntity<Void> editBoardFinish(@PathVariable final Long boardNo) {
        takeoverBoardService.editBoardFinish(boardNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardNo}/replies")
    public ResponseEntity<List<TakeoverReplyDTO>> getAnsBoardList(@PathVariable final Long boardNo) {
        final List<TakeoverReplyDTO> result = takeoverBoardService.getAnsBoardList(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{boardNo}/replies")
    public ResponseEntity<Long> addAnsBoard(
            @PathVariable final Long boardNo,
            @RequestBody final TakeoverReplyDTO replyDTO) {
        final Long replyNo = takeoverBoardService.addAnsBoard(boardNo, replyDTO);
        return ResponseEntity.ok(replyNo);
    }
}
