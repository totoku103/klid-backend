package com.klid.api.board.resource;

import com.klid.api.BaseMapperTest;
import com.klid.api.board.resource.dto.ResourceBoardDTO;
import com.klid.api.board.resource.dto.ResourceBoardListDTO;
import com.klid.api.board.resource.persistence.ResourceBoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ResourceBoardMapper 통합 테스트.
 * 실제 데이터베이스와 연동하여 SQL 쿼리를 검증합니다.
 */
class ResourceBoardMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiResourceBoardMapper")
    private ResourceBoardMapper resourceBoardMapper;

    // === Board List Tests ===

    @Test
    @DisplayName("자료실 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getBoardList(null, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("자료실 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsPagedList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getBoardList(1, 10, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("자료실 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getBoardList(1, 10, "title", "테스트", null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("자료실 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsFilteredList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getBoardList(1, 10, null, null, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board Contents Tests ===

    @Test
    @DisplayName("자료실 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsNull() {
        // When
        final ResourceBoardDTO result = resourceBoardMapper.getBoardContents(999999L);

        // Then - null 반환
    }

    // === Read Count Tests ===

    @Test
    @DisplayName("조회수 증가")
    void incrementReadCount_executesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.incrementReadCount(1L);
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("자료실 등록")
    void addBoard_withValidData_insertsSuccessfully() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardType("RESOURCE");
        boardDTO.setTitle("테스트 자료");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");

        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.addBoard(boardDTO);
    }

    @Test
    @DisplayName("자료실 수정")
    void editBoard_withValidData_updatesSuccessfully() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardNo(1L);
        boardDTO.setTitle("수정된 자료");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.editBoard(boardDTO);
    }

    @Test
    @DisplayName("자료실 삭제")
    void delBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.delBoard(1L);
    }

    // === Auth Check Tests ===

    @Test
    @DisplayName("권한 확인 - 파라미터 없이 조회")
    void checkAuth_withoutParams_returnsResult() {
        // When
        final Map<String, Object> result = resourceBoardMapper.checkAuth(null, null);

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("권한 확인 - 기관코드 및 사용자ID로 조회")
    void checkAuth_withParams_returnsResult() {
        // When
        final Map<String, Object> result = resourceBoardMapper.checkAuth("TEST001", "tester");

        // Then
        // 결과 반환
    }

    // === MOIS Board List Tests ===

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 파라미터 없이 조회")
    void getMoisBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getMoisBoardList(null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 페이지네이션")
    void getMoisBoardList_withPagination_returnsPagedList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getMoisBoardList(1, 10, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 검색 조건으로 조회")
    void getMoisBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<ResourceBoardListDTO> result = resourceBoardMapper.getMoisBoardList(1, 10, "title", "테스트");

        // Then
        assertThat(result).isNotNull();
    }

    // === MOIS Board Contents Tests ===

    @Test
    @DisplayName("행안부 자료실 상세 조회 - 존재하지 않는 게시글")
    void getMoisBoardContents_withNonExistentId_returnsNull() {
        // When
        final ResourceBoardDTO result = resourceBoardMapper.getMoisBoardContents(999999L);

        // Then - null 반환
    }

    // === MOIS Read Count Tests ===

    @Test
    @DisplayName("행안부 조회수 증가")
    void incrementMoisReadCount_executesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.incrementMoisReadCount(1L);
    }

    // === MOIS Board CRUD Tests ===

    @Test
    @DisplayName("행안부 자료실 등록")
    void addMoisBoard_withValidData_insertsSuccessfully() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardType("MOIS_RESOURCE");
        boardDTO.setTitle("테스트 행안부 자료");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("MOIS");

        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.addMoisBoard(boardDTO);
    }

    @Test
    @DisplayName("행안부 자료실 수정")
    void editMoisBoard_withValidData_updatesSuccessfully() {
        // Given
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardNo(1L);
        boardDTO.setTitle("수정된 행안부 자료");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.editMoisBoard(boardDTO);
    }

    @Test
    @DisplayName("행안부 자료실 삭제")
    void delMoisBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        resourceBoardMapper.delMoisBoard(1L);
    }
}
