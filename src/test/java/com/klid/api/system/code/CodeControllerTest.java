package com.klid.api.system.code;

import com.klid.api.BaseControllerTest;
import com.klid.api.system.code.dto.CodeRequest;
import com.klid.api.system.code.dto.WeekDayRequest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CodeController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class CodeControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/codes";

    @Test
    @DisplayName("GET /api/system/codes - 코드 목록 조회")
    void getCodeList_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("codeType", "COMMON")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/system/codes - 파라미터 없이 코드 목록 조회")
    void getCodeList_WithoutParams_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/codes - 코드 등록")
    void addCode_ReturnsOk() throws Exception {
        final CodeRequest request = new CodeRequest();
        request.setCodeType("COMMON");
        request.setCodeId("TEST001");
        request.setCodeName("테스트 코드");
        request.setCodeDesc("테스트 설명");
        request.setUseYn("Y");
        request.setSortOrder(1);

        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/system/codes/{codeId} - 코드 수정")
    void editCode_ReturnsOk() throws Exception {
        final CodeRequest request = new CodeRequest();
        request.setCodeType("COMMON");
        request.setCodeName("수정된 코드명");
        request.setCodeDesc("수정된 설명");
        request.setUseYn("Y");

        mockMvc.perform(put(BASE_URL + "/TEST001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/codes/check-duplicate - 코드 중복 체크")
    void checkCodeDuplicate_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/check-duplicate")
                        .param("codeType", "COMMON")
                        .param("codeId", "TEST001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/system/codes/weekdays - 공휴일 등록")
    void addWeekDay_ReturnsOk() throws Exception {
        final WeekDayRequest request = new WeekDayRequest();
        request.setWeekdayDate("20250101");
        request.setWeekdayName("신정");
        request.setWeekdayType("HOLIDAY");
        request.setUseYn("Y");

        mockMvc.perform(post(BASE_URL + "/weekdays")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /api/system/codes/weekdays/{weekdayId} - 공휴일 삭제")
    void deleteWeekDay_ReturnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/weekdays/WD001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
