package com.klid.api.system.customer;

import com.klid.api.BaseMapperTest;
import com.klid.api.system.customer.dto.CustomerUserResponse;
import com.klid.api.system.customer.dto.SmsGroupRequest;
import com.klid.api.system.customer.dto.SmsGroupResponse;
import com.klid.api.system.customer.persistence.CustomerUserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CustomerUserMapper 통합 테스트
 */
class CustomerUserMapperTest extends BaseMapperTest {

    @Autowired
    private CustomerUserMapper customerUserMapper;

    @Test
    @DisplayName("selectSmsUserList - 전체 내부 사용자 목록 조회")
    void selectSmsUserList_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<CustomerUserResponse> result = customerUserMapper.selectSmsUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectSmsUserList - 기관코드로 필터링")
    void selectSmsUserList_WithInstCode_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("instCode", "INST001");

        // when
        final List<CustomerUserResponse> result = customerUserMapper.selectSmsUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectSmsUserList - 사용자명으로 검색")
    void selectSmsUserList_WithUserName_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("userName", "테스트");

        // when
        final List<CustomerUserResponse> result = customerUserMapper.selectSmsUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectSmsOfUserList - 전체 외부 사용자 목록 조회")
    void selectSmsOfUserList_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<CustomerUserResponse> result = customerUserMapper.selectSmsOfUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectSmsOfUserList - 부서명으로 필터링")
    void selectSmsOfUserList_WithDeptName_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("deptName", "개발팀");

        // when
        final List<CustomerUserResponse> result = customerUserMapper.selectSmsOfUserList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectUserPhone - 사용자 전화번호 조회")
    void selectUserPhone_ReturnsPhoneOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("userId", "NON_EXISTENT_USER");

        // when
        final String result = customerUserMapper.selectUserPhone(params);

        // then
        // 존재하지 않는 사용자의 경우 null 반환
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("selectSmsGroupList - 전체 SMS 그룹 목록 조회")
    void selectSmsGroupList_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<SmsGroupResponse> result = customerUserMapper.selectSmsGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectSmsGroupList - 사용 여부로 필터링")
    void selectSmsGroupList_WithUseYn_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", "Y");

        // when
        final List<SmsGroupResponse> result = customerUserMapper.selectSmsGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectSmsGroupList - 그룹명으로 검색")
    void selectSmsGroupList_WithGroupName_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("groupName", "테스트");

        // when
        final List<SmsGroupResponse> result = customerUserMapper.selectSmsGroupList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("insertSmsGroup - SMS 그룹 등록")
    void insertSmsGroup_Success() {
        // given
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupId("MAPPER_TEST_GRP");
        request.setGroupName("매퍼 테스트 그룹");
        request.setGroupDesc("매퍼 테스트용 그룹 설명");
        request.setUseYn("Y");
        request.setUserIds(Arrays.asList("USER001", "USER002"));

        // when & then - 예외가 발생하지 않으면 성공
        customerUserMapper.insertSmsGroup(request);
    }

    @Test
    @DisplayName("updateSmsGroup - SMS 그룹 수정")
    void updateSmsGroup_Success() {
        // given
        final SmsGroupRequest request = new SmsGroupRequest();
        request.setGroupId("EXISTING_GROUP");
        request.setGroupName("수정된 그룹명");
        request.setGroupDesc("수정된 설명");
        request.setUseYn("N");

        // when & then - 예외가 발생하지 않으면 성공
        customerUserMapper.updateSmsGroup(request);
    }

    @Test
    @DisplayName("deleteSmsGroup - SMS 그룹 삭제")
    void deleteSmsGroup_Success() {
        // given
        final String groupId = "NON_EXISTENT_GROUP";

        // when & then - 예외가 발생하지 않으면 성공
        customerUserMapper.deleteSmsGroup(groupId);
    }
}
