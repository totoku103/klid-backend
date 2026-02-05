package com.klid.api.admin.popup.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 팝업 관리 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("팝업 관리 Controller 테스트")
class PopupControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/admin/popup";

    // ========== 페이지 관련 테스트 ==========

    @Test
    @DisplayName("페이지 추가 - 성공")
    void testAddPage_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuName", "테스트페이지");
        params.put("menuKind", "USER");
        params.put("isWebuse", "Y");
        params.put("visibleOrder", 1);

        // when & then
        mockMvc.perform(post(BASE_URL + "/pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("페이지 추가 - 빈 데이터")
    void testAddPage_EmptyData() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        mockMvc.perform(post(BASE_URL + "/pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("페이지 수정 - 성공")
    void testSavePage_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuName", "수정된페이지");
        params.put("isWebuse", "N");

        // when & then
        mockMvc.perform(put(BASE_URL + "/pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("페이지 수정 - 필수 필드만")
    void testSavePage_MinimalFields() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);

        // when & then
        mockMvc.perform(put(BASE_URL + "/pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("페이지 삭제 - 성공")
    void testDelPage_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);

        // when & then
        mockMvc.perform(delete(BASE_URL + "/pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("페이지 삭제 - 빈 파라미터")
    void testDelPage_EmptyParams() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        mockMvc.perform(delete(BASE_URL + "/pages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    // ========== 페이지 그룹 관련 테스트 ==========

    @Test
    @DisplayName("페이지 그룹 추가 - 성공")
    void testAddPageGroup_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("pageGroupName", "테스트그룹");
        params.put("menuPageNo", 1L);
        params.put("menuAuth", "ADMIN");

        // when & then
        mockMvc.perform(post(BASE_URL + "/page-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("페이지 그룹 추가 - 최소 정보")
    void testAddPageGroup_MinimalInfo() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("pageGroupName", "최소그룹");

        // when & then
        mockMvc.perform(post(BASE_URL + "/page-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 성공")
    void testSavePageGroup_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);
        params.put("pageGroupName", "수정된그룹");
        params.put("menuAuth", "USER");

        // when & then
        mockMvc.perform(put(BASE_URL + "/page-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("페이지 그룹 수정 - 부분 수정")
    void testSavePageGroup_PartialUpdate() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);
        params.put("menuAuth", "ADMIN");

        // when & then
        mockMvc.perform(put(BASE_URL + "/page-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("페이지 그룹 삭제 - 성공")
    void testDelPageGroup_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);

        // when & then
        mockMvc.perform(delete(BASE_URL + "/page-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("페이지 그룹 삭제 - 여러 조건")
    void testDelPageGroup_MultipleConditions() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuPageGrpNo", 1L);
        params.put("menuPageNo", 1L);

        // when & then
        mockMvc.perform(delete(BASE_URL + "/page-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    // ========== 메뉴 관련 테스트 ==========

    @Test
    @DisplayName("메뉴 추가 - 성공")
    void testAddMenu_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNm", "테스트메뉴");
        params.put("menuPageGrpNo", 1L);
        params.put("guid", "test-guid-123");
        params.put("addYn", 1);

        // when & then
        mockMvc.perform(post(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("메뉴 추가 - 필수 필드만")
    void testAddMenu_RequiredFieldsOnly() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNm", "최소메뉴");
        params.put("guid", "minimal-guid");

        // when & then
        mockMvc.perform(post(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("메뉴 수정 - 성공")
    void testSaveMenu_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuNm", "수정된메뉴");
        params.put("addYn", 0);

        // when & then
        mockMvc.perform(put(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("메뉴 수정 - 단일 필드 수정")
    void testSaveMenu_SingleFieldUpdate() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuNm", "새이름");

        // when & then
        mockMvc.perform(put(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("메뉴 삭제 - 성공")
    void testDelMenu_Success() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);

        // when & then
        mockMvc.perform(delete(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("메뉴 삭제 - GUID로 삭제")
    void testDelMenu_ByGuid() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("guid", "test-guid-123");

        // when & then
        mockMvc.perform(delete(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("메뉴 삭제 - 복합 조건")
    void testDelMenu_MultipleConditions() throws Exception {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("menuNo", 1L);
        params.put("menuPageGrpNo", 1L);

        // when & then
        mockMvc.perform(delete(BASE_URL + "/menus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(params)))
                .andExpect(status().isNoContent());
    }
}
