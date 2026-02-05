package com.klid.api.board.share.controller;

import com.klid.api.board.share.dto.ShareBoardDTO;
import com.klid.api.board.share.dto.ShareBoardListDTO;
import com.klid.api.board.share.dto.ShareBoardSidoCntDTO;
import com.klid.api.board.share.service.ShareBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/share")
public class ShareController {

    private final ShareBoardService shareBoardService;

    @GetMapping("/list")
    public ResponseEntity<List<ShareBoardListDTO>> getBoardList(
            @RequestParam(required = false) final Integer pageNo,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText,
            @RequestParam(required = false) final String sInstCd) {
        final List<ShareBoardListDTO> result = shareBoardService.getBoardList(
                pageNo, pageSize, searchType, searchText, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<ShareBoardDTO> getBoardContents(@PathVariable final Long boardNo) {
        final ShareBoardDTO result = shareBoardService.getBoardContents(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Long> addBoard(@RequestBody final ShareBoardDTO boardDTO) {
        final Long boardNo = shareBoardService.addBoard(boardDTO);
        return ResponseEntity.ok(boardNo);
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> editBoard(
            @PathVariable final Long boardNo,
            @RequestBody final ShareBoardDTO boardDTO) {
        shareBoardService.editBoard(boardNo, boardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> delBoard(@PathVariable final Long boardNo) {
        shareBoardService.delBoard(boardNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sido-count")
    public ResponseEntity<List<ShareBoardSidoCntDTO>> getShareBoardSidoCnt(
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText) {
        final List<ShareBoardSidoCntDTO> result = shareBoardService.getShareBoardSidoCnt(
                searchType, searchText);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, Object>> checkAuth(
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String userId) {
        final Map<String, Object> result = shareBoardService.checkAuth(sInstCd, userId);
        return ResponseEntity.ok(result);
    }
}
