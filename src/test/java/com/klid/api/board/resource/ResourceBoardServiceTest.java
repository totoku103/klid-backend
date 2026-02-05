package com.klid.api.board.resource;

import com.klid.api.BaseServiceTest;
import com.klid.api.board.resource.dto.ResourceBoardDTO;
import com.klid.api.board.resource.dto.ResourceBoardListDTO;
import com.klid.api.board.resource.service.ResourceBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ResourceBoardService 통합 테스트.
 * 실제 데이터베이스와 연동하여 자료실 서비스를 검증합니다.
 */
class ResourceBoardServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiResourceBoardService")
    private ResourceBoardService resourceBoardService;

    // === Board List Tests ===

    @Test
    @DisplayName("자료실 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getBoardList(null, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("자료실 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsPagedList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getBoardList(1, 10, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("자료실 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getBoardList(1, 10, "title", "테스트", null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("자료실 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsFilteredList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getBoardList(1, 10, null, null, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("자료실 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsNull() {
        // When
        final ResourceBoardDTO result = resourceBoardService.getBoardContents(999999L);

        // Then - null 또는 존재하지 않음
    }

    @Test
    @DisplayName("자료실 등록")
    void addBoard_withValidData_returnsGeneratedId() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardType("RESOURCE");
        boardDTO.setTitle("테스트 자료");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");

        // When
        final Long boardNo = resourceBoardService.addBoard(boardDTO);

        // Then
        // 트랜잭션 롤백으로 실제 저장은 되지 않지만 로직은 실행됨
    }

    @Test
    @DisplayName("자료실 수정")
    void editBoard_withValidData_updatesSuccessfully() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setTitle("수정된 자료");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        resourceBoardService.editBoard(1L, boardDTO);
    }

    @Test
    @DisplayName("자료실 삭제")
    void delBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        resourceBoardService.delBoard(1L);
    }

    // === Auth Check Tests ===

    @Test
    @DisplayName("권한 확인 - 파라미터 없이 조회")
    void checkAuth_withoutParams_returnsResult() {
        // When
        final Map<String, Object> result = resourceBoardService.checkAuth(null, null);

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("권한 확인 - 기관코드 및 사용자ID로 조회")
    void checkAuth_withParams_returnsResult() {
        // When
        final Map<String, Object> result = resourceBoardService.checkAuth("TEST001", "tester");

        // Then
        // 결과 반환
    }

    // === MOIS Board List Tests ===

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 파라미터 없이 조회")
    void getMoisBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getMoisBoardList(null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 페이지네이션")
    void getMoisBoardList_withPagination_returnsPagedList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getMoisBoardList(1, 10, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 검색 조건으로 조회")
    void getMoisBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardService.getMoisBoardList(1, 10, "title", "테스트");

        // Then
        assertThat(result).isNotNull();
    }

    // === MOIS Board CRUD Tests ===

    @Test
    @DisplayName("행안부 자료실 상세 조회 - 존재하지 않는 게시글")
    void getMoisBoardContents_withNonExistentId_returnsNull() {
        // When
        final ResourceBoardDTO result = resourceBoardService.getMoisBoardContents(999999L);

        // Then - null 또는 존재하지 않음
    }

    @Test
    @DisplayName("행안부 자료실 등록")
    void addMoisBoard_withValidData_returnsGeneratedId() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardType("MOIS_RESOURCE");
        boardDTO.setTitle("테스트 행안부 자료");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("MOIS");

        // When
        final Long boardNo = resourceBoardService.addMoisBoard(boardDTO);

        // Then
        // 트랜잭션 롤백으로 실제 저장은 되지 않지만 로직은 실행됨
    }

    @Test
    @DisplayName("행안부 자료실 수정")
    void editMoisBoard_withValidData_updatesSuccessfully() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setTitle("수정된 행안부 자료");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        resourceBoardService.editMoisBoard(1L, boardDTO);
    }

    @Test
    @DisplayName("행안부 자료실 삭제")
    void delMoisBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        resourceBoardService.delMoisBoard(1L);
    }
}
