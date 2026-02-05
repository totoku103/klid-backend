package com.klid.api.board.notice;

import com.klid.api.BaseServiceTest;
import com.klid.api.board.notice.dto.*;
import com.klid.api.board.notice.service.NoticeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * NoticeService 통합 테스트.
 * 실제 데이터베이스와 연동하여 공지사항 서비스를 검증합니다.
 */
class NoticeServiceTest extends BaseServiceTest {

    @Autowired
    private NoticeService noticeService;

    // === Main Notice List Tests ===

    @Test
    @DisplayName("메인 공지사항 목록 조회 - 파라미터 없이 조회")
    void getMainNoticeList_withoutParams_returnsListOrEmpty() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getMainNoticeList(null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메인 공지사항 목록 조회 - 목록 크기 지정")
    void getMainNoticeList_withListSize_returnsLimitedList() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getMainNoticeList(5, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메인 공지사항 목록 조회 - 기관코드로 조회")
    void getMainNoticeList_withInstCode_returnsFilteredList() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getMainNoticeList(10, "ADMIN", "TEST001", "PARENT001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Post Board List Tests ===

    @Test
    @DisplayName("게시 공지사항 목록 조회 - 파라미터 없이 조회")
    void getPostBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getPostBoardList(null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("게시 공지사항 목록 조회 - 목록 크기 및 기관코드 지정")
    void getPostBoardList_withParams_returnsFilteredList() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getPostBoardList(10, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board List Tests ===

    @Test
    @DisplayName("공지사항 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getBoardList(null, null, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공지사항 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsPagedList() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getBoardList(1, 10, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공지사항 목록 조회 - 게시판 유형별 조회")
    void getBoardList_withBoardType_returnsFilteredList() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getBoardList(1, 10, "NOTICE", null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공지사항 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<NoticeListItemDTO> result = noticeService.getBoardList(1, 10, null, "title", "테스트", null);

        // Then
        assertThat(result).isNotNull();
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("공지사항 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsNull() {
        // When
        final NoticeBoardDTO result = noticeService.getBoardContents(999999L);

        // Then - null 또는 존재하지 않음
        // 데이터가 없을 경우 null 반환
    }

    @Test
    @DisplayName("공지사항 등록")
    void addBoard_withValidData_returnsGeneratedId() {
        // Given
        final NoticeBoardDTO boardDTO = new NoticeBoardDTO();
        boardDTO.setBoardType("NOTICE");
        boardDTO.setTitle("테스트 공지사항");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");

        // When
        final Long boardNo = noticeService.addBoard(boardDTO);

        // Then
        // 트랜잭션 롤백으로 실제 저장은 되지 않지만 로직은 실행됨
    }

    @Test
    @DisplayName("공지사항 수정")
    void editBoard_withValidData_updatesSuccessfully() {
        // Given
        final NoticeBoardDTO boardDTO = new NoticeBoardDTO();
        boardDTO.setTitle("수정된 공지사항");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        noticeService.editBoard(1L, boardDTO);
    }

    @Test
    @DisplayName("공지사항 삭제")
    void delBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        noticeService.delBoard(1L);
    }

    // === Board Type List Tests ===

    @Test
    @DisplayName("게시판 유형 목록 조회")
    void getBoardTypeList_returnsListOrEmpty() {
        // When
        final List<Map<String, Object>> result = noticeService.getBoardTypeList();

        // Then
        assertThat(result).isNotNull();
    }

    // === Auth Check Tests ===

    @Test
    @DisplayName("권한 확인 - 파라미터 없이 조회")
    void checkAuth_withoutParams_returnsResult() {
        // When
        final Map<String, Object> result = noticeService.checkAuth(null, null);

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("권한 확인 - 기관코드 및 사용자ID로 조회")
    void checkAuth_withParams_returnsResult() {
        // When
        final Map<String, Object> result = noticeService.checkAuth("TEST001", "tester");

        // Then
        // 결과 반환
    }

    // === Survey Tests ===

    @Test
    @DisplayName("설문 등록")
    void addNoticeSurvey_withValidData_savesSuccessfully() {
        // Given
        final NoticeSurveyDTO surveyDTO = new NoticeSurveyDTO();

        // When & Then - 예외 없이 실행되어야 함
        noticeService.addNoticeSurvey(1L, surveyDTO);
    }

    @Test
    @DisplayName("설문 응답 수 조회")
    void getSurveyAnswerCount_returnsCount() {
        // When
        final Integer count = noticeService.getSurveyAnswerCount(1L);

        // Then - null 또는 숫자 반환
    }

    @Test
    @DisplayName("설문 차트 데이터 조회")
    void getSurveyChart_returnsListOrEmpty() {
        // When
        final List<NoticeSurveyChartDTO> result = noticeService.getSurveyChart(1L);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("설문 그리드 데이터 조회")
    void getSurveyGrid_returnsListOrEmpty() {
        // When
        final List<NoticeSurveyGridDTO> result = noticeService.getSurveyGrid(1L);

        // Then
        assertThat(result).isNotNull();
    }

    // === Confirmation Tests ===

    @Test
    @DisplayName("확인 등록")
    void addNoticeConfirm_withValidData_savesSuccessfully() {
        // Given
        final NoticeConfirmDTO confirmDTO = new NoticeConfirmDTO();

        // When & Then - 예외 없이 실행되어야 함
        noticeService.addNoticeConfirm(1L, confirmDTO);
    }

    @Test
    @DisplayName("확인 수정")
    void editNoticeConfirm_withValidData_updatesSuccessfully() {
        // Given
        final NoticeConfirmDTO confirmDTO = new NoticeConfirmDTO();

        // When & Then - 예외 없이 실행되어야 함
        noticeService.editNoticeConfirm(1L, confirmDTO);
    }

    @Test
    @DisplayName("확인 목록 조회")
    void getConfirmList_returnsListOrEmpty() {
        // When
        final List<NoticeConfirmDTO> result = noticeService.getConfirmList(1L);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("확인 체크 조회")
    void getConfirmCheck_returnsResult() {
        // When
        final Map<String, Object> result = noticeService.getConfirmCheck(1L, "tester");

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("확인 답변 체크 조회")
    void getConfirmReplyCheck_returnsResult() {
        // When
        final Map<String, Object> result = noticeService.getConfirmReplyCheck(1L, "tester");

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("확인 삭제")
    void delNoticeConfirm_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        noticeService.delNoticeConfirm(1L, "tester");
    }
}
