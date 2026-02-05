package com.klid.api.board.qna;

import com.klid.api.BaseControllerTest;
import com.klid.api.board.qna.dto.QnABoardDTO;
import com.klid.api.board.qna.dto.QnACommentDTO;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * QnAController 통합 테스트.
 * 실제 데이터베이스와 연동하여 Q&A API를 검증합니다.
 */
@Import(TestSecurityConfig.class)
class QnAControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/board/qna";

    // === Main QnA List Tests ===

    @Test
    @DisplayName("메인 Q&A 목록 조회 - 파라미터 없이 조회")
    void getMainQnaList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/main-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("메인 Q&A 목록 조회 - 목록 크기 지정")
    void getMainQnaList_withListSize_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/main-list")
                        .param("listSize", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("메인 Q&A 목록 조회 - 기관코드로 조회")
    void getMainQnaList_withInstCode_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/main-list")
                        .param("listSize", "10")
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Post Board List Tests ===

    @Test
    @DisplayName("게시 Q&A 목록 조회 - 파라미터 없이 조회")
    void getPostBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/post-list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("게시 Q&A 목록 조회 - 목록 크기 및 기관코드 지정")
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
    @DisplayName("Q&A 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Q&A 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Q&A 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("searchType", "title")
                        .param("searchText", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Q&A 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("Q&A 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Q&A 등록")
    void addBoard_withValidData_returnsOk() throws Exception {
        final QnABoardDTO boardDTO = new QnABoardDTO();
        boardDTO.setBoardType("QNA");
        boardDTO.setTitle("테스트 Q&A");
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
    @DisplayName("Q&A 수정")
    void editBoard_withValidData_returnsOk() throws Exception {
        final QnABoardDTO boardDTO = new QnABoardDTO();
        boardDTO.setTitle("수정된 Q&A");
        boardDTO.setContent("수정된 내용입니다.");

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Q&A 삭제")
    void delBoard_withValidId_returnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // === Comment Tests ===

    @Test
    @DisplayName("댓글 등록")
    void addComment_withValidData_returnsOk() throws Exception {
        final QnACommentDTO commentDTO = new QnACommentDTO();
        commentDTO.setContent("테스트 댓글입니다.");

        mockMvc.perform(post(BASE_URL + "/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(commentDTO)))
                .andExpect(status().isOk());
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
}
