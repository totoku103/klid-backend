package com.klid.api.board.notice.controller;

import com.klid.api.board.notice.dto.NoticeListItemDTO;
import com.klid.api.board.notice.service.NoticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class NoticeControllerTest {

    @Mock
    private NoticeService noticeService;

    private NoticeController controller;

    @BeforeEach
    void setUp() {
        controller = new NoticeController(noticeService);
    }

    @Nested
    @DisplayName("getMainNoticeList()")
    class GetMainNoticeList {

        @Test
        @DisplayName("공지사항 메인 리스트를 정상적으로 반환한다")
        void returnsMainNoticeListSuccessfully() {
            final List<NoticeListItemDTO> dtoList = Arrays.asList(
                    createNoticeListItemDTO("1", "공지사항 제목1", "2024-01-15"),
                    createNoticeListItemDTO("2", "공지사항 제목2", "2024-01-14"),
                    createNoticeListItemDTO("3", "공지사항 제목3", "2024-01-13")
            );
            given(noticeService.getMainNoticeList(6, "AUTH_MAIN_2", "1100000", "1100000"))
                    .willReturn(dtoList);

            final ResponseEntity<List<NoticeListItemDTO>> response = controller.getMainNoticeList(
                    6, "AUTH_MAIN_2", "1100000", "1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(3, response.getBody().size());
            assertEquals("공지사항 제목1", response.getBody().get(0).getBultnTitle());
        }

        @Test
        @DisplayName("결과가 없으면 빈 리스트를 반환한다")
        void returnsEmptyListWhenNoData() {
            given(noticeService.getMainNoticeList(6, "AUTH_MAIN_2", "1100000", "1100000"))
                    .willReturn(Collections.emptyList());

            final ResponseEntity<List<NoticeListItemDTO>> response = controller.getMainNoticeList(
                    6, "AUTH_MAIN_2", "1100000", "1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertTrue(response.getBody().isEmpty());
        }

        @Test
        @DisplayName("선택적 파라미터가 null이어도 정상 처리한다")
        void handlesNullParameters() {
            given(noticeService.getMainNoticeList(null, null, null, null))
                    .willReturn(Collections.emptyList());

            final ResponseEntity<List<NoticeListItemDTO>> response = controller.getMainNoticeList(
                    null, null, null, null);

            assertEquals(HttpStatus.OK, response.getStatusCode());
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
