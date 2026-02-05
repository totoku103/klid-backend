package com.klid.api.system.customer;

import com.klid.api.BaseControllerTest;
import com.klid.api.system.customer.dto.SmsGroupRequest;
import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * CustomerUserController 통합 테스트
 */
@Import(TestSecurityConfig.class)
class CustomerUserControllerTest extends BaseControllerTest {

    private static final String BASE_URL = "/api/system/customer-users";

    @Test
    @DisplayName("POST /api/system/customer-users/sms-users - 내부 사용자 목록 조회")
    void getSmsUserList_ReturnsOk() throws Exception {
        mockMvc.perform(post(BASE_URL + "/sms-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/system/customer-users/sms-users - 파라미터로 필터링")
    void getSmsUserList_WithParams_ReturnsOk() throws Exception {
        mockMvc.perform(post(BASE_URL + "/sms-users")
                        .param("instCode", "INST001")
                        .param("userName", "홍길동")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/customer-users/sms-external-users - 외부 사용자 목록 조회")
    void getSmsExternalUserList_ReturnsOk() throws Exception {
        mockMvc.perform(post(BASE_URL + "/sms-external-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("POST /api/system/customer-users/sms-external-users - 파라미터로 필터링")
    void getSmsExternalUserList_WithParams_ReturnsOk() throws Exception {
        mockMvc.perform(post(BASE_URL + "/sms-external-users")
                        .param("deptName", "개발팀")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/customer-users/phone - 사용자 전화번호 조회")
    void getUserPhone_ReturnsOk() throws Exception {
        mockMvc.perform(post(BASE_URL + "/phone")
                        .param("userId", "USER001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/system/customer-users/sms-groups - SMS 그룹 목록 조회")
    void getSmsGroupList_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/sms-groups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/system/customer-users/sms-groups - 파라미터로 필터링")
    void getSmsGroupList_WithParams_ReturnsOk() throws Exception {
        mockMvc.perform(get(BASE_URL + "/sms-groups")
                        .param("useYn", "Y")
                        .param("groupName", "테스트")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/customer-users/sms-groups - SMS 그룹 등록")
    void addSmsGroup_ReturnsOk() throws Exception {
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupName("테스트 그룹");
        request.setGroupDesc("테스트 그룹 설명");
        request.setUseYn("Y");
        request.setUserIds(Arrays.asList("USER001", "USER002", "USER003"));

        mockMvc.perform(post(BASE_URL + "/sms-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/system/customer-users/sms-groups/{groupId} - SMS 그룹 수정")
    void editSmsGroup_ReturnsOk() throws Exception {
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupName("수정된 그룹명");
        request.setGroupDesc("수정된 설명");
        request.setUseYn("Y");
        request.setUserIds(Arrays.asList("USER001", "USER004"));

        mockMvc.perform(put(BASE_URL + "/sms-groups/GROUP001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /api/system/customer-users/sms-groups/{groupId} - SMS 그룹 삭제")
    void deleteSmsGroup_ReturnsOk() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/sms-groups/GROUP001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/system/customer-users/sms-groups - 빈 userIds로 그룹 등록")
    void addSmsGroup_WithEmptyUserIds_ReturnsOk() throws Exception {
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupName("빈 그룹");
        request.setGroupDesc("사용자 없는 그룹");
        request.setUseYn("Y");

        mockMvc.perform(post(BASE_URL + "/sms-groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))
                .andExpect(status().isOk());
    }
}
