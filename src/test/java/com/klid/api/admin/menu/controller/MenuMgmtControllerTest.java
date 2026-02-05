package com.klid.api.admin.menu.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 메뉴 관리 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("메뉴 관리 Controller 테스트")
class MenuMgmtControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/admin/menu-management";

    @Test
    @DisplayName("페이지 목록 조회 - 성공")
    void testGetPageList_Success() throws Exception {
        mockMvc.perform(get(BASE_URL + "/pages"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("페이지 목록 조회 - 파라미터와 함께")
    void testGetPageList_WithParameters() throws Exception {
        mockMvc.perform(get(BASE_URL + "/pages")
                        .param("menuName", "테스트메뉴")
                        .param("isWebuse", "Y"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 성공")
    void testGetPageGroupList_Success() throws Exception {
        mockMvc.perform(get(BASE_URL + "/page-groups"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("페이지 그룹 목록 조회 - 파라미터와 함께")
    void testGetPageGroupList_WithParameters() throws Exception {
        mockMvc.perform(get(BASE_URL + "/page-groups")
                        .param("menuAuth", "ADMIN")
                        .param("siteName", "KLID"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 성공")
    void testGetMenuList_Success() throws Exception {
        mockMvc.perform(get(BASE_URL + "/menus"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 파라미터와 함께")
    void testGetMenuList_WithParameters() throws Exception {
        mockMvc.perform(get(BASE_URL + "/menus")
                        .param("menuKind", "USER")
                        .param("visibleOrder", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("메뉴 목록 조회 - 빈 파라미터로 조회")
    void testGetMenuList_EmptyParams() throws Exception {
        mockMvc.perform(get(BASE_URL + "/menus"))
                .andExpect(status().isOk());
    }
}
