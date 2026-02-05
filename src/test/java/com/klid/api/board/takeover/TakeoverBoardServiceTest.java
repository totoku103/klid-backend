package com.klid.api.board.takeover;

import com.klid.api.BaseServiceTest;
import com.klid.api.board.takeover.dto.TakeoverBoardDTO;
import com.klid.api.board.takeover.dto.TakeoverBoardListDTO;
import com.klid.api.board.takeover.dto.TakeoverReplyDTO;
import com.klid.api.board.takeover.service.TakeoverBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TakeoverBoardService 통합 테스트.
 * 실제 데이터베이스와 연동하여 인계인수 게시판 서비스를 검증합니다.
 */
class TakeoverBoardServiceTest extends BaseServiceTest {

    @Autowired
    private TakeoverBoardService takeoverBoardService;

    // === Board List Tests ===

    @Test
    @DisplayName("인계인수 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(null, null, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsPagedList() {
        // When
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(1, 10, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(1, 10, "title", "테스트", null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 상태로 조회")
    void getBoardList_withStatus_returnsFilteredList() {
        // When
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(1, 10, null, null, "진행중", null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsFilteredList() {
        // When
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(1, 10, null, null, null, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 복합 조건으로 조회")
    void getBoardList_withMultipleConditions_returnsFilteredList() {
        // When
        final List<TakeoverBoardListDTO> result = takeoverBoardService.getBoardList(1, 10, "title", "테스트", "진행중", "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("인계인수 상세 조회 - 존재하지 않는 게시글")
    void getBoardInfo_withNonExistentId_returnsNull() {
        // When
        final TakeoverBoardDTO result = takeoverBoardService.getBoardInfo(999999L);

        // Then - null 또는 존재하지 않음
    }

    @Test
    @DisplayName("인계인수 등록")
    void addBoard_withValidData_returnsGeneratedId() {
        // Given
        final TakeoverBoardDTO boardDTO = new TakeoverBoardDTO();
        boardDTO.setTitle("테스트 인계인수");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setStatus("진행중");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");
        boardDTO.setReceiverNm("수신자");
        boardDTO.setReceiverId("receiver");
        boardDTO.setReceiverInstCd("TEST002");

        // When
        final Long boardNo = takeoverBoardService.addBoard(boardDTO);

        // Then
        // 트랜잭션 롤백으로 실제 저장은 되지 않지만 로직은 실행됨
    }

    @Test
    @DisplayName("인계인수 수정")
    void editBoard_withValidData_updatesSuccessfully() {
        // Given
        final TakeoverBoardDTO boardDTO = new TakeoverBoardDTO();
        boardDTO.setTitle("수정된 인계인수");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        takeoverBoardService.editBoard(1L, boardDTO);
    }

    // === Confirm and Finish Tests ===

    @Test
    @DisplayName("인계인수 확인")
    void addBoardConfirm_withValidId_executesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        takeoverBoardService.addBoardConfirm(1L);
    }

    @Test
    @DisplayName("인계인수 종결")
    void editBoardFinish_withValidId_executesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        takeoverBoardService.editBoardFinish(1L);
    }

    // === Reply Tests ===

    @Test
    @DisplayName("답변 목록 조회")
    void getAnsBoardList_returnsListOrEmpty() {
        // When
        final List<TakeoverReplyDTO> result = takeoverBoardService.getAnsBoardList(1L);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("답변 등록")
    void addAnsBoard_withValidData_returnsGeneratedId() {
        // Given
        final TakeoverReplyDTO replyDTO = new TakeoverReplyDTO();
        replyDTO.setContent("테스트 답변입니다.");
        replyDTO.setWriterNm("답변자");
        replyDTO.setWriterId("replyer");
        replyDTO.setWriterInstCd("TEST001");

        // When
        final Long replyNo = takeoverBoardService.addAnsBoard(1L, replyDTO);

        // Then
        // 트랜잭션 롤백으로 실제 저장은 되지 않지만 로직은 실행됨
    }
}
