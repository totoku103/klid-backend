package com.klid.api.common.code.controller;

import com.klid.api.BaseControllerTest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 공통 코드 Controller 통합 테스트
 */
@Import(TestSecurityConfig.class)
@DisplayName("공통 코드 Controller 테스트")
class CommonCodeControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/common-codes";

    @Test
    @DisplayName("공통 코드 목록 조회 - 파라미터 없음")
    void testGetCommonCodes_NoParams() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - comCode1 파라미터")
    void testGetCommonCodes_WithComCode1() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("comCode1", "SYS"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - comCode1, comCode2 파라미터")
    void testGetCommonCodes_WithComCode1AndComCode2() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("comCode1", "SYS")
                        .param("comCode2", "001"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - 전체 파라미터")
    void testGetCommonCodes_WithAllParams() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("comCode1", "SYS")
                        .param("comCode2", "001")
                        .param("comCode3", "001")
                        .param("codeLvl", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - codeLvl만 지정")
    void testGetCommonCodes_WithCodeLvlOnly() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("codeLvl", "2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }
}
