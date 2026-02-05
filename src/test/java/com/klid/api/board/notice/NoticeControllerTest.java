package com.klid.api.board.notice;

import com.klid.api.BaseControllerTest;
import com.klid.api.board.notice.dto.NoticeBoardDTO;
import com.klid.api.board.notice.dto.NoticeConfirmDTO;
import com.klid.api.board.notice.dto.NoticeSurveyDTO;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * NoticeController 통합 테스트.
 * 실제 데이터베이스와 연동하여 공지사항 API를 검증합니다.
 */
@Import(TestSecurityConfig.class)
class NoticeControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/board/notice";

    // === Main Notice List Tests ===

    @Test
    @DisplayName("메인 공지사항 목록 조회 - 파라미터 없이 조회")
    void getMainNoticeList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/main-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("메인 공지사항 목록 조회 - 목록 크기 지정")
    void getMainNoticeList_withListSize_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/main-list")
                        .param("listSize", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("메인 공지사항 목록 조회 - 기관코드로 조회")
    void getMainNoticeList_withInstCode_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/main-list")
                        .param("sInstCd", "TEST001")
                        .param("sAuthMain", "ADMIN")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Post Board List Tests ===

    @Test
    @DisplayName("게시 공지사항 목록 조회 - 파라미터 없이 조회")
    void getPostBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/post-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("게시 공지사항 목록 조회 - 목록 크기 및 기관코드 지정")
    void getPostBoardList_withParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/post-list")
                        .param("listSize", "10")
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Board List Tests ===

    @Test
    @DisplayName("공지사항 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("공지사항 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("공지사항 목록 조회 - 게시판 유형별 조회")
    void getBoardList_withBoardType_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("boardType", "NOTICE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("공지사항 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("searchType", "title")
                        .param("searchText", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("공지사항 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공지사항 등록")
    void addBoard_withValidData_returnsOk() throws Exception {
        final NoticeBoardDTO boardDTO = new NoticeBoardDTO();
        boardDTO.setBoardType("NOTICE");
        boardDTO.setTitle("테스트 공지사항");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("TEST001");

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공지사항 수정")
    void editBoard_withValidData_returnsOk() throws Exception {
        final NoticeBoardDTO boardDTO = new NoticeBoardDTO();
        boardDTO.setTitle("수정된 공지사항");
        boardDTO.setContent("수정된 내용입니다.");

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공지사항 삭제")
    void delBoard_withValidId_returnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // === Board Type List Tests ===

    @Test
    @DisplayName("게시판 유형 목록 조회")
    void getBoardTypeList_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/board-types")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Auth Check Tests ===

    @Test
    @DisplayName("권한 확인 - 파라미터 없이 조회")
    void checkAuth_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/check-auth")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("권한 확인 - 기관코드 및 사용자ID로 조회")
    void checkAuth_withParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/check-auth")
                        .param("sInstCd", "TEST001")
                        .param("userId", "tester")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Survey Tests ===

    @Test
    @DisplayName("설문 등록")
    void addNoticeSurvey_withValidData_returnsOk() throws Exception {
        final NoticeSurveyDTO surveyDTO = new NoticeSurveyDTO();

        mockMvc.perform(post(BASE_URL + "/1/survey")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(surveyDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("설문 응답 수 조회")
    void getSurveyAnswerCount_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/survey/answer-count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("설문 차트 데이터 조회")
    void getSurveyChart_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/survey/chart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("설문 그리드 데이터 조회")
    void getSurveyGrid_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/survey/grid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Confirmation Tests ===

    @Test
    @DisplayName("확인 등록")
    void addNoticeConfirm_withValidData_returnsOk() throws Exception {
        final NoticeConfirmDTO confirmDTO = new NoticeConfirmDTO();

        mockMvc.perform(post(BASE_URL + "/1/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(confirmDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("확인 수정")
    void editNoticeConfirm_withValidData_returnsOk() throws Exception {
        final NoticeConfirmDTO confirmDTO = new NoticeConfirmDTO();

        mockMvc.perform(put(BASE_URL + "/1/confirm")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(confirmDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("확인 목록 조회")
    void getConfirmList_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/confirm/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("확인 체크 조회")
    void getConfirmCheck_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/confirm/check")
                        .param("userId", "tester")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("확인 답변 체크 조회")
    void getConfirmReplyCheck_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/confirm/reply-check")
                        .param("userId", "tester")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("확인 삭제")
    void delNoticeConfirm_returnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1/confirm")
                        .param("userId", "tester")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
