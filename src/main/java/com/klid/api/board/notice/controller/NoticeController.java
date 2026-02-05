package com.klid.api.board.notice.controller;

import com.klid.api.board.notice.dto.NoticeListItemDTO;
import com.klid.api.board.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
