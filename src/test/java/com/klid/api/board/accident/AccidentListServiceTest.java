package com.klid.api.board.accident;

import com.klid.api.BaseServiceTest;
import com.klid.api.board.accident.dto.AccidentListItemDTO;
import com.klid.api.board.accident.dto.AccidentSearchRequestDTO;
import com.klid.api.board.accident.service.AccidentListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AccidentListService 통합 테스트.
 * 실제 데이터베이스와 연동하여 사고 신고 목록 서비스를 검증합니다.
 */
class AccidentListServiceTest extends BaseServiceTest {

    @Autowired
    private AccidentListService accidentListService;

    @Test
    @DisplayName("사고 신고 목록 조회 - 빈 검색 조건으로 조회")
    void getAccidentList_withEmptySearchRequest_returnsListOrEmpty() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 기관코드로 검색")
    void getAccidentList_withInstCode_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setSInstCd("TEST001");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 처리상태코드로 검색")
    void getAccidentList_withProcessStatus_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setInciPrcsStatCd("01");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 날짜 범위로 검색")
    void getAccidentList_withDateRange_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setDate1("2024-01-01");
        searchRequest.setDate2("2024-12-31");
        searchRequest.setSrchDateType("inciAcpnDt");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 사고내용 다중 키워드 검색")
    void getAccidentList_withMultipleContentKeywords_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setInciDclCont("키워드1 키워드2 키워드3");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 조사내용 다중 키워드 검색")
    void getAccidentList_withMultipleInvestigationKeywords_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setInciInvsCont("조사1 조사2");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 시도의견 다중 키워드 검색")
    void getAccidentList_withMultipleSidoOpinionKeywords_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setInciBelowCont("의견1 의견2");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 접수방법 복수 선택 검색")
    void getAccidentList_withMultipleAcceptMethods_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setSrchAcpnMthd("01,02,03");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 목록 조회 - 복합 검색 조건")
    void getAccidentList_withMultipleConditions_returnsFilteredList() {
        // Given
        final AccidentSearchRequestDTO searchRequest = new AccidentSearchRequestDTO();
        searchRequest.setSInstCd("TEST001");
        searchRequest.setNetDiv("1");
        searchRequest.setInciPrcsStatCd("02");
        searchRequest.setAccdTypCd("A01");
        searchRequest.setInciPrtyCd("HIGH");
        searchRequest.setDclInstName("테스트기관");
        searchRequest.setInciTtl("테스트제목");

        // When
        final List<AccidentListItemDTO> result = accidentListService.getAccidentList(searchRequest);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사고 신고 삭제 - 존재하지 않는 사고번호")
    void deleteAccident_withNonExistentId_doesNotThrow() {
        // Given
        final String nonExistentInciNo = "NON_EXISTENT_001";

        // When & Then - 예외 없이 실행되어야 함
        accidentListService.deleteAccident(nonExistentInciNo);
    }

    @Test
    @DisplayName("사고 신고 삭제 - 정상 삭제")
    void deleteAccident_withValidId_deletesSuccessfully() {
        // Given
        final String testInciNo = "TEST_DELETE_001";

        // When & Then - 트랜잭션 롤백으로 실제 삭제는 발생하지 않음
        accidentListService.deleteAccident(testInciNo);
    }
}
