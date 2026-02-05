package com.klid.api.board.notice.service;

import com.klid.api.board.notice.dto.NoticeListItemDTO;
import com.klid.api.board.notice.persistence.NoticeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {

    @Mock
    private NoticeMapper noticeMapper;

    private NoticeService service;

    @BeforeEach
    void setUp() {
        service = new NoticeService(noticeMapper);
    }

    @Nested
    @DisplayName("getMainNoticeList()")
    class GetMainNoticeList {

        @Test
        @DisplayName("Mapper로부터 공지사항 리스트를 조회하여 반환한다")
        void returnsNoticeListFromMapper() {
            final List<NoticeListItemDTO> expected = Arrays.asList(
                    createNoticeListItemDTO("1", "공지사항1", "2024-01-15"),
                    createNoticeListItemDTO("2", "공지사항2", "2024-01-14")
            );
            given(noticeMapper.getMainNoticeList(6, "AUTH_MAIN_2", "1100000", "1100000"))
                    .willReturn(expected);

            final List<NoticeListItemDTO> result = service.getMainNoticeList(6, "AUTH_MAIN_2", "1100000", "1100000");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("공지사항1", result.get(0).getBultnTitle());
        }

        @Test
        @DisplayName("Mapper가 빈 리스트를 반환하면 빈 리스트를 반환한다")
        void returnsEmptyListWhenMapperReturnsEmptyList() {
            given(noticeMapper.getMainNoticeList(6, "AUTH_MAIN_2", "1100000", "1100000"))
                    .willReturn(Collections.emptyList());

            final List<NoticeListItemDTO> result = service.getMainNoticeList(6, "AUTH_MAIN_2", "1100000", "1100000");

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    private NoticeListItemDTO createNoticeListItemDTO(final String bultnNo, final String title, final String regDate) {
        final NoticeListItemDTO dto = new NoticeListItemDTO();
        dto.setBultnNo(bultnNo);
        dto.setBultnTitle(title);
        dto.setRegDate(regDate);
        return dto;
    }
}
