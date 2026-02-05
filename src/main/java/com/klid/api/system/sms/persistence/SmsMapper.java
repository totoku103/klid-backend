package com.klid.api.system.sms.persistence;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SMS 전송 Mapper
 */
@Component("apiSmsMapper")
@Mapper
public interface SmsMapper {

    void insertMessage(Map<String, Object> params);
}
