package com.klid.api.system.customer.persistence;

import com.klid.api.system.customer.dto.CustomerUserRequest;
import com.klid.api.system.customer.dto.CustomerUserResponse;
import com.klid.api.system.customer.dto.SmsGroupRequest;
import com.klid.api.system.customer.dto.SmsGroupResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 외부 사용자 관리 Mapper
 */
@Mapper
public interface CustomerUserMapper {

    List<CustomerUserResponse> selectSmsUserList(Map<String, Object> params);

    List<CustomerUserResponse> selectSmsOfUserList(Map<String, Object> params);

    String selectUserPhone(Map<String, Object> params);

    List<SmsGroupResponse> selectSmsGroupList(Map<String, Object> params);

    void insertSmsGroup(SmsGroupRequest request);

    void updateSmsGroup(SmsGroupRequest request);

    void deleteSmsGroup(String groupId);
}
