package com.klid.api.board.qna.controller;

import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.service.QnAService;
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
class QnAControllerTest {

    @Mock
    private QnAService qnAService;

    private QnAController controller;

    @BeforeEach
    void setUp() {
        controller = new QnAController(qnAService);
    }

    @Nested
    @DisplayName("getMainQnaList()")
    class GetMainQnaList {

        @Test
        @DisplayName("QnA 메인 리스트를 정상적으로 반환한다")
        void returnsMainQnaListSuccessfully() {
            final List<QnAListItemDTO> dtoList = Arrays.asList(
                    createQnAListItemDTO("1", "문의 제목1", "2024-01-15", "N", "user1"),
                    createQnAListItemDTO("2", "문의 제목2", "2024-01-14", "Y", "user2")
            );
            given(qnAService.getMainQnaList(6, "1100000")).willReturn(dtoList);

            final ResponseEntity<List<QnAListItemDTO>> response = controller.getMainQnaList(6, "1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals(2, response.getBody().size());
            assertEquals("문의 제목1", response.getBody().get(0).getBultnTitle());
            assertEquals("N", response.getBody().get(0).getIsSecret());
        }

        @Test
        @DisplayName("비밀글 여부가 Y인 항목을 올바르게 반환한다")
        void returnsSecretQnaCorrectly() {
            final List<QnAListItemDTO> dtoList = Arrays.asList(
                    createQnAListItemDTO("1", "비밀 문의", "2024-01-15", "Y", "user1")
            );
            given(qnAService.getMainQnaList(6, "1100000")).willReturn(dtoList);

            final ResponseEntity<List<QnAListItemDTO>> response = controller.getMainQnaList(6, "1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(response.getBody());
            assertEquals("Y", response.getBody().get(0).getIsSecret());
        }

        @Test
        @DisplayName("결과가 없으면 빈 리스트를 반환한다")
        void returnsEmptyListWhenNoData() {
            given(qnAService.getMainQnaList(6, "1100000")).willReturn(Collections.emptyList());

            final ResponseEntity<List<QnAListItemDTO>> response = controller.getMainQnaList(6, "1100000");

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertTrue(response.getBody().isEmpty());
        }
    }

    private QnAListItemDTO createQnAListItemDTO(final String bultnNo, final String title, final String regDate,
                                                 final String isSecret, final String userId) {
        final QnAListItemDTO dto = new QnAListItemDTO();
        dto.setBultnNo(bultnNo);
        dto.setBultnTitle(title);
        dto.setRegDate(regDate);
        dto.setIsSecret(isSecret);
        dto.setUserId(userId);
        return dto;
    }
}
