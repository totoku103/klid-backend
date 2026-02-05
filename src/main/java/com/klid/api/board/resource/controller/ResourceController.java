package com.klid.api.board.resource.controller;

import com.klid.api.board.resource.dto.ResourceBoardDTO;
import com.klid.api.board.resource.dto.ResourceBoardListDTO;
import com.klid.api.board.resource.service.ResourceBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/resource")
public class ResourceController {

    private final ResourceBoardService resourceBoardService;

    @GetMapping("/list")
    public ResponseEntity<List<ResourceBoardListDTO>> getBoardList(
            @RequestParam(required = false) final Integer pageNo,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText,
            @RequestParam(required = false) final String sInstCd) {
        final List<ResourceBoardListDTO> result = resourceBoardService.getBoardList(
                pageNo, pageSize, searchType, searchText, sInstCd);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{boardNo}")
    public ResponseEntity<ResourceBoardDTO> getBoardContents(@PathVariable final Long boardNo) {
        final ResourceBoardDTO result = resourceBoardService.getBoardContents(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Long> addBoard(@RequestBody final ResourceBoardDTO boardDTO) {
        final Long boardNo = resourceBoardService.addBoard(boardDTO);
        return ResponseEntity.ok(boardNo);
    }

    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> editBoard(
            @PathVariable final Long boardNo,
            @RequestBody final ResourceBoardDTO boardDTO) {
        resourceBoardService.editBoard(boardNo, boardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> delBoard(@PathVariable final Long boardNo) {
        resourceBoardService.delBoard(boardNo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, Object>> checkAuth(
            @RequestParam(required = false) final String sInstCd,
            @RequestParam(required = false) final String userId) {
        final Map<String, Object> result = resourceBoardService.checkAuth(sInstCd, userId);
        return ResponseEntity.ok(result);
    }

    // MOIS (행안부) Board Endpoints
    @GetMapping("/mois/list")
    public ResponseEntity<List<ResourceBoardListDTO>> getMoisBoardList(
            @RequestParam(required = false) final Integer pageNo,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final String searchType,
            @RequestParam(required = false) final String searchText) {
        final List<ResourceBoardListDTO> result = resourceBoardService.getMoisBoardList(
                pageNo, pageSize, searchType, searchText);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/mois/{boardNo}")
    public ResponseEntity<ResourceBoardDTO> getMoisBoardContents(@PathVariable final Long boardNo) {
        final ResourceBoardDTO result = resourceBoardService.getMoisBoardContents(boardNo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/mois")
    public ResponseEntity<Long> addMoisBoard(@RequestBody final ResourceBoardDTO boardDTO) {
        final Long boardNo = resourceBoardService.addMoisBoard(boardDTO);
        return ResponseEntity.ok(boardNo);
    }

    @PutMapping("/mois/{boardNo}")
    public ResponseEntity<Void> editMoisBoard(
            @PathVariable final Long boardNo,
            @RequestBody final ResourceBoardDTO boardDTO) {
        resourceBoardService.editMoisBoard(boardNo, boardDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/mois/{boardNo}")
    public ResponseEntity<Void> delMoisBoard(@PathVariable final Long boardNo) {
        resourceBoardService.delMoisBoard(boardNo);
        return ResponseEntity.ok().build();
    }
}
