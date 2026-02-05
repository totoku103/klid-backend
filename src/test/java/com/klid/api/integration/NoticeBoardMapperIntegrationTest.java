package com.klid.api.integration;

import com.klid.webapp.main.sec.noticeBoard.dto.NoticeBoardDto;
import com.klid.webapp.main.sec.noticeBoard.persistence.NoticeBoardMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * NoticeBoardMapper 통합 테스트
 * - 실제 데이터베이스(10.1.2.99)를 사용
 * - 트랜잭션 롤백으로 데이터 정합성 유지
 * - 공지사항 게시판 CRUD 전체 테스트
 */
@DisplayName("NoticeBoardMapper 통합 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoticeBoardMapperIntegrationTest extends IntegrationTestBase {

    @Autowired
    private NoticeBoardMapper noticeBoardMapper;

    private static final String TEST_USER_ID = "test_integration";
    private static final String TEST_BOARD_TITLE = "통합테스트 공지사항";
    private static final String TEST_BOARD_CONTENT = "통합테스트용 공지사항 내용입니다.";

    @Test
    @Order(1)
    @DisplayName("공지사항 목록 조회 - SELECT")
    void getBoardList_shouldReturnNoticeList() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sAuthMain", "AUTH_MAIN_2");

        // When
        final List<NoticeBoardDto> result = noticeBoardMapper.getBoardList(paramMap);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("메인 공지사항 목록 조회")
    void getMainNoticeList_shouldReturnMainNoticeList() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("listSize", 5);
        paramMap.put("sAuthMain", "AUTH_MAIN_2");

        // When
        final List<NoticeBoardDto> result = noticeBoardMapper.getMainNoticeList(paramMap);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isLessThanOrEqualTo(5);
    }

    @Test
    @Order(3)
    @DisplayName("게시판 타입 목록 조회")
    void getBoardTypeList_shouldReturnBoardTypes() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupType", "notice");

        // When
        final List<NoticeBoardDto> result = noticeBoardMapper.getBoardTypeList(paramMap);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(4)
    @DisplayName("공지사항 등록 - INSERT")
    void addBoard_shouldInsertNewNotice() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("boardTitle", TEST_BOARD_TITLE);
        paramMap.put("boardContent", TEST_BOARD_CONTENT);
        paramMap.put("userId", TEST_USER_ID);
        paramMap.put("organCode", "1100000");
        paramMap.put("cateNo", "1");
        paramMap.put("groupItemNo", "1");
        paramMap.put("noticeScope", "1,2");
        paramMap.put("surveyTypeCode", "");
        paramMap.put("userName", "테스트사용자");
        paramMap.put("control", "1");
        paramMap.put("endDt", "");

        // When
        noticeBoardMapper.addBoard(paramMap);
        final int boardNo = (int) paramMap.get("boardNo");

        // Then
        assertThat(boardNo).isGreaterThan(0);

        // 등록된 공지사항 확인
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("boardNo", boardNo);
        final NoticeBoardDto notice = noticeBoardMapper.getBoardContents(selectParam);

        assertThat(notice).isNotNull();
        assertThat(notice.getBultnTitle()).isEqualTo(TEST_BOARD_TITLE);
    }

    @Test
    @Order(5)
    @DisplayName("공지사항 상세 조회")
    void getBoardContents_shouldReturnNoticeDetail() {
        // Given - 먼저 공지사항 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("boardTitle", "상세조회테스트");
        insertParam.put("boardContent", "상세조회 테스트 내용");
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("organCode", "1100000");
        insertParam.put("cateNo", "1");
        insertParam.put("groupItemNo", "1");
        insertParam.put("noticeScope", "1,2");
        insertParam.put("surveyTypeCode", "");
        insertParam.put("userName", "테스트사용자");
        insertParam.put("control", "1");
        insertParam.put("endDt", "");

        noticeBoardMapper.addBoard(insertParam);
        final int boardNo = (int) insertParam.get("boardNo");

        // When
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("boardNo", boardNo);
        final NoticeBoardDto result = noticeBoardMapper.getBoardContents(selectParam);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getBultnTitle()).isEqualTo("상세조회테스트");
        assertThat(result.getBultnCont()).isEqualTo("상세조회 테스트 내용");
    }

    @Test
    @Order(6)
    @DisplayName("공지사항 수정 - UPDATE")
    void editBoard_shouldUpdateNotice() {
        // Given - 먼저 공지사항 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("boardTitle", "수정전제목");
        insertParam.put("boardContent", "수정전내용");
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("organCode", "1100000");
        insertParam.put("cateNo", "1");
        insertParam.put("groupItemNo", "1");
        insertParam.put("noticeScope", "1,2");
        insertParam.put("surveyTypeCode", "");
        insertParam.put("userName", "테스트사용자");
        insertParam.put("control", "1");
        insertParam.put("endDt", "");

        noticeBoardMapper.addBoard(insertParam);
        final int boardNo = (int) insertParam.get("boardNo");

        // When - 공지사항 수정
        final Map<String, Object> updateParam = new HashMap<>();
        updateParam.put("boardNo", boardNo);
        updateParam.put("boardTitle", "수정후제목");
        updateParam.put("boardContent", "수정후내용");
        updateParam.put("noticeScope", "1,2");
        updateParam.put("control", "2");
        updateParam.put("cateNo", "2");
        updateParam.put("groupItemNo", "1");

        noticeBoardMapper.editBoard(updateParam);

        // Then - 수정된 내용 확인
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("boardNo", boardNo);
        final NoticeBoardDto result = noticeBoardMapper.getBoardContents(selectParam);

        assertThat(result).isNotNull();
        assertThat(result.getBultnTitle()).isEqualTo("수정후제목");
        assertThat(result.getBultnCont()).isEqualTo("수정후내용");
    }

    @Test
    @Order(7)
    @DisplayName("조회수 증가 테스트")
    void hitsUpBoard_shouldIncrementReadCount() {
        // Given - 먼저 공지사항 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("boardTitle", "조회수테스트");
        insertParam.put("boardContent", "조회수 테스트 내용");
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("organCode", "1100000");
        insertParam.put("cateNo", "1");
        insertParam.put("groupItemNo", "1");
        insertParam.put("noticeScope", "1,2");
        insertParam.put("surveyTypeCode", "");
        insertParam.put("userName", "테스트사용자");
        insertParam.put("control", "1");
        insertParam.put("endDt", "");

        noticeBoardMapper.addBoard(insertParam);
        final int boardNo = (int) insertParam.get("boardNo");

        // 초기 조회수 확인
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("boardNo", boardNo);
        NoticeBoardDto notice = noticeBoardMapper.getBoardContents(selectParam);
        final int initialReadCnt = notice.getReadCnt();

        // When - 조회수 증가
        final Map<String, Object> hitsParam = new HashMap<>();
        hitsParam.put("boardNo", boardNo);
        noticeBoardMapper.hitsUpBoard(hitsParam);

        // Then - 조회수 확인
        notice = noticeBoardMapper.getBoardContents(selectParam);
        assertThat(notice.getReadCnt()).isEqualTo(initialReadCnt + 1);
    }

    @Test
    @Order(8)
    @DisplayName("공지사항 삭제 - DELETE (논리삭제)")
    void delBoard_shouldLogicalDeleteNotice() {
        // Given - 먼저 공지사항 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("boardTitle", "삭제테스트");
        insertParam.put("boardContent", "삭제 테스트 내용");
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("organCode", "1100000");
        insertParam.put("cateNo", "1");
        insertParam.put("groupItemNo", "1");
        insertParam.put("noticeScope", "1,2");
        insertParam.put("surveyTypeCode", "");
        insertParam.put("userName", "테스트사용자");
        insertParam.put("control", "1");
        insertParam.put("endDt", "");

        noticeBoardMapper.addBoard(insertParam);
        final int boardNo = (int) insertParam.get("boardNo");

        // When - 공지사항 삭제
        final Map<String, Object> deleteParam = new HashMap<>();
        deleteParam.put("boardNo", boardNo);
        noticeBoardMapper.delBoard(deleteParam);

        // Then - 삭제 확인 (논리삭제이므로 USE_YN = 'N'으로 변경됨)
        // getBoardContents는 USE_YN = 'Y'인 것만 조회하므로 null이어야 함
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("boardNo", boardNo);
        final NoticeBoardDto result = noticeBoardMapper.getBoardContents(selectParam);

        assertThat(result).isNull();
    }

    @Test
    @Order(9)
    @DisplayName("기간별 공지사항 조회")
    void getPeriodBoardList_shouldReturnNoticesInPeriod() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDt", "20240101000000");
        paramMap.put("endDt", "20261231235959");
        paramMap.put("sAuthMain", "AUTH_MAIN_2");

        // When
        final List<NoticeBoardDto> result = noticeBoardMapper.getPeriodBoardList(paramMap);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(10)
    @DisplayName("공지사항 확인 등록")
    void addNoticeConfirm_shouldInsertConfirmation() {
        // Given - 먼저 공지사항 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("boardTitle", "확인테스트");
        insertParam.put("boardContent", "확인 테스트 내용");
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("organCode", "1100000");
        insertParam.put("cateNo", "1");
        insertParam.put("groupItemNo", "1");
        insertParam.put("noticeScope", "1,2");
        insertParam.put("surveyTypeCode", "");
        insertParam.put("userName", "테스트사용자");
        insertParam.put("control", "1");
        insertParam.put("endDt", "");

        noticeBoardMapper.addBoard(insertParam);
        final int boardNo = (int) insertParam.get("boardNo");

        // When - 확인 등록
        final Map<String, Object> confirmParam = new HashMap<>();
        confirmParam.put("bultnNo", boardNo);
        confirmParam.put("userId", TEST_USER_ID);
        confirmParam.put("confirmContent", "확인했습니다.");

        final int confirmResult = noticeBoardMapper.addNoticeConfirm(confirmParam);

        // Then
        assertThat(confirmResult).isGreaterThan(0);

        // 확인 여부 체크
        final Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("bultnNo", boardNo);
        checkParam.put("sUserId", TEST_USER_ID);
        final int confirmCheck = noticeBoardMapper.selectConfirmCheck(checkParam);

        assertThat(confirmCheck).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Order(11)
    @DisplayName("작성 횟수 제한 확인")
    void selectCreateCnt_shouldReturnCreateCount() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("boardType", "notice");
        paramMap.put("userId", TEST_USER_ID);

        // When
        final int result = noticeBoardMapper.selectCreateCnt(paramMap);

        // Then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(12)
    @DisplayName("CRUD 전체 사이클 테스트")
    void fullCRUDCycle_shouldWorkCorrectly() {
        // CREATE
        final Map<String, Object> createParam = new HashMap<>();
        createParam.put("boardTitle", "CRUD사이클테스트");
        createParam.put("boardContent", "CRUD 전체 사이클 테스트 내용");
        createParam.put("userId", TEST_USER_ID);
        createParam.put("organCode", "1100000");
        createParam.put("cateNo", "1");
        createParam.put("groupItemNo", "1");
        createParam.put("noticeScope", "1,2");
        createParam.put("surveyTypeCode", "");
        createParam.put("userName", "테스트사용자");
        createParam.put("control", "1");
        createParam.put("endDt", "");

        noticeBoardMapper.addBoard(createParam);
        final int boardNo = (int) createParam.get("boardNo");
        assertThat(boardNo).isGreaterThan(0);

        // READ
        final Map<String, Object> readParam = new HashMap<>();
        readParam.put("boardNo", boardNo);
        NoticeBoardDto notice = noticeBoardMapper.getBoardContents(readParam);

        assertThat(notice).isNotNull();
        assertThat(notice.getBultnTitle()).isEqualTo("CRUD사이클테스트");

        // UPDATE
        final Map<String, Object> updateParam = new HashMap<>();
        updateParam.put("boardNo", boardNo);
        updateParam.put("boardTitle", "CRUD사이클수정됨");
        updateParam.put("boardContent", "수정된 CRUD 테스트 내용");
        updateParam.put("noticeScope", "1,2");
        updateParam.put("control", "2");
        updateParam.put("cateNo", "2");
        updateParam.put("groupItemNo", "1");

        noticeBoardMapper.editBoard(updateParam);

        // READ after UPDATE
        notice = noticeBoardMapper.getBoardContents(readParam);
        assertThat(notice).isNotNull();
        assertThat(notice.getBultnTitle()).isEqualTo("CRUD사이클수정됨");

        // DELETE
        final Map<String, Object> deleteParam = new HashMap<>();
        deleteParam.put("boardNo", boardNo);
        noticeBoardMapper.delBoard(deleteParam);

        // READ after DELETE
        notice = noticeBoardMapper.getBoardContents(readParam);
        assertThat(notice).isNull();
    }
}
