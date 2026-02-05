package com.klid.api.board.resource;

import com.klid.api.BaseControllerTest;
import com.klid.api.board.resource.dto.ResourceBoardDTO;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ResourceController 통합 테스트.
 * 실제 데이터베이스와 연동하여 자료실 API를 검증합니다.
 */
@Import(TestSecurityConfig.class)
class ResourceControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/board/resource";

    // === Board List Tests ===

    @Test
    @DisplayName("자료실 목록 조회 - 파라미터 없이 조회")
    void getBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("자료실 목록 조회 - 페이지네이션")
    void getBoardList_withPagination_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("자료실 목록 조회 - 검색 조건으로 조회")
    void getBoardList_withSearchCondition_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("searchType", "title")
                        .param("searchText", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("자료실 목록 조회 - 기관코드로 조회")
    void getBoardList_withInstCode_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/list")
                        .param("sInstCd", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === Board CRUD Tests ===

    @Test
    @DisplayName("자료실 상세 조회 - 존재하지 않는 게시글")
    void getBoardContents_withNonExistentId_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("자료실 등록")
    void addBoard_withValidData_returnsOk() throws Exception {
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardType("RESOURCE");
        boardDTO.setTitle("테스트 자료");
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
    @DisplayName("자료실 수정")
    void editBoard_withValidData_returnsOk() throws Exception {
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setTitle("수정된 자료");
        boardDTO.setContent("수정된 내용입니다.");

        mockMvc.perform(put(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("자료실 삭제")
    void delBoard_withValidId_returnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
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

    // === MOIS Board List Tests ===

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 파라미터 없이 조회")
    void getMoisBoardList_withoutParams_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/mois/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 페이지네이션")
    void getMoisBoardList_withPagination_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/mois/list")
                        .param("pageNo", "1")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("행안부 자료실 목록 조회 - 검색 조건으로 조회")
    void getMoisBoardList_withSearchCondition_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/mois/list")
                        .param("searchType", "title")
                        .param("searchText", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // === MOIS Board CRUD Tests ===

    @Test
    @DisplayName("행안부 자료실 상세 조회 - 존재하지 않는 게시글")
    void getMoisBoardContents_withNonExistentId_returnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/mois/999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("행안부 자료실 등록")
    void addMoisBoard_withValidData_returnsOk() throws Exception {
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setBoardType("MOIS_RESOURCE");
        boardDTO.setTitle("테스트 행안부 자료");
        boardDTO.setContent("테스트 내용입니다.");
        boardDTO.setWriterNm("테스터");
        boardDTO.setWriterId("tester");
        boardDTO.setWriterInstCd("MOIS");

        mockMvc.perform(post(BASE_URL + "/mois")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("행안부 자료실 수정")
    void editMoisBoard_withValidData_returnsOk() throws Exception {
        final ResourceBoardDTO boardDTO = new ResourceBoardDTO();
        boardDTO.setTitle("수정된 행안부 자료");
        boardDTO.setContent("수정된 내용입니다.");

        mockMvc.perform(put(BASE_URL + "/mois/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(boardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("행안부 자료실 삭제")
    void delMoisBoard_withValidId_returnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/mois/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
