package com.klid.api.system.sms.persistence;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * SMS 전송 Mapper
 */
@Repository
public interface SmsMapper {

    void insertMessage(Map<String, Object> params);
}
