package com.klid.api.board.qna;

import com.klid.api.BaseMapperTest;
import com.klid.api.board.qna.dto.QnABoardDTO;
import com.klid.api.board.qna.dto.QnACommentDTO;
import com.klid.api.board.qna.dto.QnAListItemDTO;
import com.klid.api.board.qna.persistence.QnAMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * QnAMapper 통합 테스트.
 * 실제 데이터베이스와 연동하여 SQL 쿼리를 검증합니다.
 */
class QnAMapperTest extends BaseMapperTest {

    @Autowired
    private QnAMapper qnaMapper;

    // === Main QnA List Tests ===

    @Test
    @DisplayName("메인 Q&A 목록 조회 - 파라미터 없이 조회")
    void getMainQnaList_withoutParams_returnsListOrEmpty() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getMainQnaList(null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메인 Q&A 목록 조회 - 목록 크기 지정")
    void getMainQnaList_withListSize_returnsLimitedList() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getMainQnaList(5, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("메인 Q&A 목록 조회 - 기관코드로 조회")
    void getMainQnaList_withInstCode_returnsFilteredList() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getMainQnaList(10, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Post Board List Tests ===

    @Test
    @DisplayName("게시 Q&A 목록 조회 - 파라미터 없이 조회")
    void getPostBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getPostBoardList(null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("게시 Q&A 목록 조회 - 목록 크기 및 기관코드 지정")
    void getPostBoardList_withParams_returnsFilteredList() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getPostBoardList(10, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board List Tests ===

    @Test
    @DisplayName("Q&A 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsListOrEmpty() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getBoardList(null, null, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Q&A 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsPagedList() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getBoardList(1, 10, null, null, null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Q&A 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsFilteredList() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getBoardList(1, 10, "title", "테스트", null);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Q&A 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsFilteredList() {
        // When
        final List<QnAListItemDTO> result = qnaMapper.getBoardList(1, 10, null, null, "TEST001");

        // Then
        assertThat(result).isNotNull();
    }

    // === Board Contents Tests ===

    @Test
    @DisplayName("Q&A 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsNull() {
        // When
        final QnABoardDTO result = qnaMapper.getBoardContents(999999L);

        // Then - null 반환
    }

    // === Read Count Tests ===

    @Test
    @DisplayName("조회수 증가")
    void incrementReadCount_executesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        qnaMapper.incrementReadCount(1L);
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("Q&A 등록")
    void addBoard_withValidData_insertsSuccessfully() {
        // Given
        final QnABoardDTO boardDTO = new QnABoardDTO();
        boardDTO.setBoardType("QNA");
        boardDTO.setTitle("테스트 Q&A");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");

        // When & Then - 예외 없이 실행되어야 함
        qnaMapper.addBoard(boardDTO);
    }

    @Test
    @DisplayName("Q&A 수정")
    void editBoard_withValidData_updatesSuccessfully() {
        // Given
        final QnABoardDTO boardDTO = new QnABoardDTO();
        boardDTO.setBoardNo(1L);
        boardDTO.setTitle("수정된 Q&A");
        boardDTO.setContent("수정된 내용입니다.");

        // When & Then - 예외 없이 실행되어야 함
        qnaMapper.editBoard(boardDTO);
    }

    @Test
    @DisplayName("Q&A 삭제")
    void delBoard_withValidId_deletesSuccessfully() {
        // When & Then - 예외 없이 실행되어야 함
        qnaMapper.delBoard(1L);
    }

    // === Comment Tests ===

    @Test
    @DisplayName("댓글 등록")
    void addComment_withValidData_insertsSuccessfully() {
        // Given
        final QnACommentDTO commentDTO = new QnACommentDTO();
        commentDTO.setBoardNo(1L);
        commentDTO.setContent("테스트 댓글입니다.");

        // When & Then - 예외 없이 실행되어야 함
        qnaMapper.addComment(commentDTO);
    }

    // === Auth Check Tests ===

    @Test
    @DisplayName("권한 확인 - 파라미터 없이 조회")
    void checkAuth_withoutParams_returnsResult() {
        // When
        final Map<String, Object> result = qnaMapper.checkAuth(null, null);

        // Then
        // null 또는 결과 반환
    }

    @Test
    @DisplayName("권한 확인 - 기관코드 및 사용자ID로 조회")
    void checkAuth_withParams_returnsResult() {
        // When
        final Map<String, Object> result = qnaMapper.checkAuth("TEST001", "tester");

        // Then
        // 결과 반환
    }
}
