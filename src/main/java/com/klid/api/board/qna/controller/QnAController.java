package com.klid.api.board.qna.controller;

import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
