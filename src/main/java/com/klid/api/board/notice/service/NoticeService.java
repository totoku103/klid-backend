package com.klid.api.board.notice.service;

import com.klid.api.board.notice.dto.NoticeListItemDTO;
import com.klid.api.board.notice.persistence.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
