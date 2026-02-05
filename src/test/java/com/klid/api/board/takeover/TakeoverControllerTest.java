package com.klid.api.board.takeover;

import com.klid.api.BaseControllerTest;
import com.klid.api.board.takeover.dto.TakeoverBoardDTO;
import com.klid.api.board.takeover.dto.TakeoverReplyDTO;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * TakeoverController 통합 테스트.
 * 실제 데이터베이스와 연동하여 인계인수 게시판 API를 검증합니다.
 */
@Import(TestSecurityConfig.class)
class TakeoverControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/board/takeover";

    // === Board List Tests ===

    @Test
    @DisplayName("인계인수 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("searchType", "title")
                        .param("searchText", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 상태로 조회")
    void getBoardList_withStatus_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("status", "진행중")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("인계인수 목록 조회 - 복합 조건으로 조회")
    void getBoardList_withMultipleConditions_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .param("searchType", "title")
                        .param("searchText", "테스트")
                        .param("status", "진행중")
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("인계인수 상세 조회 - 존재하지 않는 게시글")
    void getBoardInfo_withNonExistentId_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("인계인수 등록")
    void addBoard_withValidData_returnsOk() throws Exception {
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

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("인계인수 수정")
    void editBoard_withValidData_returnsOk() throws Exception {
        final TakeoverBoardDTO boardDTO = new TakeoverBoardDTO();
        boardDTO.setTitle("수정된 인계인수");
        boardDTO.setContent("수정된 내용입니다.");

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    // === Confirm and Finish Tests ===

    @Test
    @DisplayName("인계인수 확인")
    void addBoardConfirm_withValidId_returnsOk() throws Exception {
        mockMvc.perform(put(BASE_URL + "/1/confirm")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("인계인수 종결")
    void editBoardFinish_withValidId_returnsOk() throws Exception {
        mockMvc.perform(put(BASE_URL + "/1/finish")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // === Reply Tests ===

    @Test
    @DisplayName("답변 목록 조회")
    void getAnsBoardList_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/1/replies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("답변 등록")
    void addAnsBoard_withValidData_returnsOk() throws Exception {
        final TakeoverReplyDTO replyDTO = new TakeoverReplyDTO();
        replyDTO.setContent("테스트 답변입니다.");
        replyDTO.setWriterNm("답변자");
        replyDTO.setWriterId("replyer");
        replyDTO.setWriterInstCd("TEST001");

        mockMvc.perform(post(BASE_URL + "/1/replies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(replyDTO)))
                .andExpect(status().isOk());
    }
}
