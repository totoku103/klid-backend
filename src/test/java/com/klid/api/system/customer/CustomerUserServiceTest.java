package com.klid.api.system.customer;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.customer.dto.CustomerUserResponse;
import com.klid.api.system.customer.dto.SmsGroupRequest;
import com.klid.api.system.customer.dto.SmsGroupResponse;
import com.klid.api.system.customer.service.CustomerUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CustomerUserService 통합 테스트
 */
class CustomerUserServiceTest extends BaseServiceTest {

    @Autowired
    private CustomerUserService customerUserService;

    @Test
    @DisplayName("SMS 전송용 내부 사용자 목록 조회 - 빈 파라미터")
    void getSmsUserList_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<CustomerUserResponse> result = customerUserService.getSmsUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("SMS 전송용 내부 사용자 목록 조회 - 기관코드로 필터링")
    void getSmsUserList_WithInstCode_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("instCode", "INST001");

        // when
        final List<CustomerUserResponse> result = customerUserService.getSmsUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("SMS 전송용 외부 사용자 목록 조회 - 빈 파라미터")
    void getSmsOfUserList_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<CustomerUserResponse> result = customerUserService.getSmsOfUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("SMS 전송용 외부 사용자 목록 조회 - 이름으로 검색")
    void getSmsOfUserList_WithUserName_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("userName", "홍길동");

        // when
        final List<CustomerUserResponse> result = customerUserService.getSmsOfUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("사용자 전화번호 조회")
    void getUserPhone_ReturnsPhoneOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("userId", "NON_EXISTENT_USER");

        // when
        final String result = customerUserService.getUserPhone(params);

        // then
        // 존재하지 않는 사용자의 경우 null 반환
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("SMS 그룹 목록 조회 - 빈 파라미터")
    void getSmsGroup_WithEmptyParams_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<SmsGroupResponse> result = customerUserService.getSmsGroup(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("SMS 그룹 목록 조회 - 사용 여부로 필터링")
    void getSmsGroup_WithUseYn_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", "Y");

        // when
        final List<SmsGroupResponse> result = customerUserService.getSmsGroup(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("SMS 그룹 등록")
    void addSmsGroup_Success() {
        // given
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupName("서비스 테스트 그룹");
        request.setGroupDesc("서비스 테스트용 그룹 설명");
        request.setUseYn("Y");
        request.setUserIds(Arrays.asList("USER001", "USER002"));

        // when & then - 예외가 발생하지 않으면 성공
        customerUserService.addSmsGroup(request);
    }

    @Test
    @DisplayName("SMS 그룹 수정")
    void editSmsGroup_Success() {
        // given
        final String groupId = "GROUP_FOR_UPDATE";
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupName("수정된 그룹명");
        request.setGroupDesc("수정된 설명");
        request.setUseYn("N");
        request.setUserIds(Arrays.asList("USER003"));

        // when & then - 예외가 발생하지 않으면 성공
        customerUserService.editSmsGroup(groupId, request);

        // verify that groupId was set
        assertThat(request.getGroupId()).isEqualTo(groupId);
    }

    @Test
    @DisplayName("SMS 그룹 삭제")
    void delSmsGroup_Success() {
        // given
        final String groupId = "GROUP_FOR_DELETE";

        // when & then - 예외가 발생하지 않으면 성공
        customerUserService.delSmsGroup(groupId);
    }

    @Test
    @DisplayName("SMS 그룹 등록 - userIds 없이")
    void addSmsGroup_WithoutUserIds_Success() {
        // given
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupName("빈 그룹");
        request.setGroupDesc("사용자 없는 그룹");
        request.setUseYn("Y");

        // when & then - 예외가 발생하지 않으면 성공
        customerUserService.addSmsGroup(request);
    }
}
