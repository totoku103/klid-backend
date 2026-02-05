package com.klid.api.board.qna.service;

import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.persistence.QnAMapper;
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
class QnAServiceTest {

    @Mock
    private QnAMapper qnAMapper;

    private QnAService service;

    @BeforeEach
    void setUp() {
        service = new QnAService(qnAMapper);
    }

    @Nested
    @DisplayName("getMainQnaList()")
    class GetMainQnaList {

        @Test
        @DisplayName("Mapper로부터 QnA 리스트를 조회하여 반환한다")
        void returnsQnaListFromMapper() {
            final List<QnAListItemDTO> expected = Arrays.asList(
                    createQnAListItemDTO("1", "문의1", "2024-01-15", "N", "user1"),
                    createQnAListItemDTO("2", "문의2", "2024-01-14", "Y", "user2")
            );
            given(qnAMapper.getMainQnaList(6, "1100000")).willReturn(expected);

            final List<QnAListItemDTO> result = service.getMainQnaList(6, "1100000");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("문의1", result.get(0).getBultnTitle());
        }

        @Test
        @DisplayName("Mapper가 빈 리스트를 반환하면 빈 리스트를 반환한다")
        void returnsEmptyListWhenMapperReturnsEmptyList() {
            given(qnAMapper.getMainQnaList(6, "1100000")).willReturn(Collections.emptyList());

            final List<QnAListItemDTO> result = service.getMainQnaList(6, "1100000");

            assertNotNull(result);
            assertTrue(result.isEmpty());
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
