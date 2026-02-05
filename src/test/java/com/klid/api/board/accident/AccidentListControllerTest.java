package com.klid.api.board.accident;

import com.klid.api.BaseControllerTest;
import com.klid.api.board.accident.dto.AccidentSearchRequestDTO;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * AccidentListController 통합 테스트.
 * 실제 데이터베이스와 연동하여 사고 신고 목록 API를 검증합니다.
 */
@Import(TestSecurityConfig.class)
class AccidentListControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/accidents";

    @Test
    @DisplayName("사고 신고 목록 조회 - 파라미터 없이 전체 조회")
    void getAccidentList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 기관코드로 검색")
    void getAccidentList_withInstCode_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 처리상태코드로 검색")
    void getAccidentList_withProcessStatus_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("inciPrcsStatCd", "01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 사고유형코드로 검색")
    void getAccidentList_withAccidentType_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("accdTypCd", "A01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 날짜 범위로 검색")
    void getAccidentList_withDateRange_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("date1", "2024-01-01")
                        .param("date2", "2024-12-31")
                        .param("srchDateType", "inciAcpnDt")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 복합 검색 조건")
    void getAccidentList_withMultipleParams_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("sInstCd", "TEST001")
                        .param("netDiv", "1")
                        .param("inciPrcsStatCd", "02")
                        .param("accdTypCd", "A01")
                        .param("inciPrtyCd", "HIGH")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 제목으로 검색")
    void getAccidentList_withTitle_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("inciTtl", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 사고내용으로 검색 (다중 키워드)")
    void getAccidentList_withMultipleKeywords_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("inciDclCont", "키워드1 키워드2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 접수방법 복수 선택")
    void getAccidentList_withMultipleAcceptMethods_returnsFilteredList() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("srchAcpnMthd", "01,02,03")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("사고 신고 삭제 - 존재하지 않는 사고번호")
    void deleteAccident_withNonExistentId_returnsNoContent() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/NON_EXISTENT_ID")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("사고 신고 삭제 - 정상 삭제")
    void deleteAccident_withValidId_returnsNoContent() throws Exception {
        // Given: 테스트용 사고번호
        final String testInciNo = "TEST_INCI_001";

        // When & Then
        mockMvc.perform(delete(BASE_URL + "/" + testInciNo)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
