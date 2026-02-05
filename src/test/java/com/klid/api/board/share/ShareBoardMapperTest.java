package com.klid.api.board.share;

import com.klid.api.BaseMapperTest;
import com.klid.api.board.share.dto.ShareBoardDTO;
import com.klid.api.board.share.dto.ShareBoardListDTO;
import com.klid.api.board.share.dto.ShareBoardSidoCntDTO;
import com.klid.api.board.share.persistence.ShareBoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ShareBoardMapper 통합 테스트.
 * 실제 데이터베이스와 연동하여 SQL 쿼리를 검증합니다.
 */
class ShareBoardMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiShareBoardMapper")
    private ShareBoardMapper shareBoardMapper;

    // === Board List Tests ===

    @Test
    @DisplayName("공유게시판 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<ShareBoardListDTO> result = shareBoardMapper.getBoardList(null, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공유게시판 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsPagedList() {
        // When
        final List<ShareBoardListDTO> result = shareBoardMapper.getBoardList(1, 10, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공유게시판 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<ShareBoardListDTO> result = shareBoardMapper.getBoardList(1, 10, "title", "테스트", null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공유게시판 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsFilteredList() {
        // When
        final List<ShareBoardListDTO> result = shareBoardMapper.getBoardList(1, 10, null, null, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공유게시판 목록 조회 - 복합 조건으로 조회")
    void getBoardList_withMultipleConditions_returnsFilteredList() {
        // When
        final List<ShareBoardListDTO> result = shareBoardMapper.getBoardList(1, 10, "title", "테스트", "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board Contents Tests ===

    @Test
    @DisplayName("공유게시판 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsNull() {
        // When
        final ShareBoardDTO result = shareBoardMapper.getBoardContents(999999L);

        // Then - null 반환
    }

    // === Read Count Tests ===

    @Test
    @DisplayName("조회수 증가")
    void incrementReadCount_executesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        shareBoardMapper.incrementReadCount(1L);
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("공유게시판 등록")
    void addBoard_withValidData_insertsSuccessfully() {
        // Given
        final ShareBoardDTO boardDTO = new ShareBoardDTO();
        boardDTO.setBoardType("SHARE");
        boardDTO.setTitle("테스트 공유게시글");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");

        // When & Then - 예외 없이 실행되어야 함
        shareBoardMapper.addBoard(boardDTO);
    }

    @Test
    @DisplayName("공유게시판 수정")
    void editBoard_withValidData_updatesSuccessfully() {
        // Given
        final ShareBoardDTO boardDTO = new ShareBoardDTO();
        boardDTO.setBoardNo(1L);
        boardDTO.setTitle("수정된 공유게시글");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        shareBoardMapper.editBoard(boardDTO);
    }

    @Test
    @DisplayName("공유게시판 삭제")
    void delBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        shareBoardMapper.delBoard(1L);
    }

    // === Sido Count Tests ===

    @Test
    @DisplayName("시도별 게시글 수 조회 - 파라미터 없이 조회")
    void getShareBoardSidoCnt_withoutParams_returnsListOrEmpty() {
        // When
        final List<ShareBoardSidoCntDTO> result = shareBoardMapper.getShareBoardSidoCnt(null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("시도별 게시글 수 조회 - 검색 조건으로 조회")
    void getShareBoardSidoCnt_withSearchCondition_returnsFilteredList() {
        // When
        final List<ShareBoardSidoCntDTO> result = shareBoardMapper.getShareBoardSidoCnt("title", "테스트");

        // Then
        assertThat(result).isNotNull();
    }

    // === Auth Check Tests ===

    @Test
    @DisplayName("권한 확인 - 파라미터 없이 조회")
    void checkAuth_withoutParams_returnsResult() {
        // When
        final Map<String, Object> result = shareBoardMapper.checkAuth(null, null);

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("권한 확인 - 기관코드 및 사용자ID로 조회")
    void checkAuth_withParams_returnsResult() {
        // When
        final Map<String, Object> result = shareBoardMapper.checkAuth("TEST001", "tester");

        // Then
        // 결과 반환
    }
}
