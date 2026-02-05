package com.klid.api.system.customer.service;

import com.klid.api.system.customer.dto.CustomerUserRequest;
import com.klid.api.system.customer.dto.CustomerUserResponse;
import com.klid.api.system.customer.dto.SmsGroupRequest;
import com.klid.api.system.customer.dto.SmsGroupResponse;
import com.klid.api.system.customer.persistence.CustomerUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 외부 사용자 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomerUserService {

    private final CustomerUserMapper customerUserMapper;

    /**
     * SMS 전송용 내부 사용자 목록 조회
     */
    public List<CustomerUserResponse> getSmsUserList(final Map<String, Object> params) {
        return customerUserMapper.selectSmsUserList(params);
    }

    /**
     * SMS 전송용 외부 사용자 목록 조회
     */
    public List<CustomerUserResponse> getSmsOfUserList(final Map<String, Object> params) {
        return customerUserMapper.selectSmsOfUserList(params);
    }

    /**
     * 사용자 전화번호 조회
     */
    public String getUserPhone(final Map<String, Object> params) {
        return customerUserMapper.selectUserPhone(params);
    }

    /**
     * SMS 그룹 목록 조회
     */
    public List<SmsGroupResponse> getSmsGroup(final Map<String, Object> params) {
        return customerUserMapper.selectSmsGroupList(params);
    }

    /**
     * SMS 그룹 등록
     */
    @Transactional
    public void addSmsGroup(final SmsGroupRequest request) {
        customerUserMapper.insertSmsGroup(request);
    }

    /**
     * SMS 그룹 수정
     */
    @Transactional
    public void editSmsGroup(final String groupId, final SmsGroupRequest request) {
        request.setGroupId(groupId);
        customerUserMapper.updateSmsGroup(request);
    }

    /**
     * SMS 그룹 삭제
     */
    @Transactional
    public void delSmsGroup(final String groupId) {
        customerUserMapper.deleteSmsGroup(groupId);
    }
}
