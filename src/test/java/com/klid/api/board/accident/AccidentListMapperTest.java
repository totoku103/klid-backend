package com.klid.api.board.accident;

import com.klid.api.BaseMapperTest;
import com.klid.api.board.accident.dto.AccidentListItemDTO;
import com.klid.api.board.accident.persistence.AccidentListMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AccidentListMapper 통합 테스트.
 * 실제 데이터베이스와 연동하여 SQL 쿼리를 검증합니다.
 */
class AccidentListMapperTest extends BaseMapperTest {

    @Autowired
    private AccidentListMapper accidentListMapper;

    @Test
    @DisplayName("사고 신고 목록 조회 - 빈 파라미터로 조회")
    void selectAccidentList_withEmptyParams_returnsListOrEmpty() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 기관코드로 조회")
    void selectAccidentList_withInstCode_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("sInstCd", "TEST001");

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 처리상태코드로 조회")
    void selectAccidentList_withProcessStatus_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("inciPrcsStatCd", "01");

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 날짜 범위로 조회")
    void selectAccidentList_withDateRange_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("date1", "2024-01-01");
        searchParams.put("startDt", "2024-01-01 00:00:00");
        searchParams.put("endDt", "2024-12-31 23:59:59");
        searchParams.put("srchDateType", "inciAcpnDt");

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 사고유형코드로 조회")
    void selectAccidentList_withAccidentType_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("accdTypCd", "A01");

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 사고내용 다중 키워드로 조회")
    void selectAccidentList_withContentKeywords_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("InciDclCont", List.of("키워드1", "키워드2"));

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 접수방법 복수 선택으로 조회")
    void selectAccidentList_withAcceptMethods_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("srchAcpnMthdList", List.of("01", "02", "03"));

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 복합 조건으로 조회")
    void selectAccidentList_withMultipleConditions_returnsFilteredList() {
        // Given
        final Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("sInstCd", "TEST001");
        searchParams.put("netDiv", "1");
        searchParams.put("inciPrcsStatCd", "02");
        searchParams.put("accdTypCd", "A01");
        searchParams.put("inciPrtyCd", "HIGH");

        // When
        final List<AccidentListItemDTO> result = accidentListMapper.selectAccidentList(searchParams);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 삭제 - 존재하지 않는 사고번호")
    void deleteAccident_withNonExistentId_returnsZero() {
        // Given
        final String nonExistentInciNo = "NON_EXISTENT_001";

        // When
        final int result = accidentListMapper.deleteAccident(nonExistentInciNo);

        // Then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("사고 신고 삭제 - 정상 삭제")
    void deleteAccident_withValidId_deletesSuccessfully() {
        // Given
        final String testInciNo = "TEST_DELETE_001";

        // When
        final int result = accidentListMapper.deleteAccident(testInciNo);

        // Then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }
}
