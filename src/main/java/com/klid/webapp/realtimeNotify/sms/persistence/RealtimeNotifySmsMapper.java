package com.klid.webapp.realtimeNotify.sms.persistence;

import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * RealtimeNotify SMS MyBatis Mapper
 * NRMSG_DATA 테이블에 SMS 메시지 삽입
 */
@Repository("realtimeNotifySmsMapper")
public interface RealtimeNotifySmsMapper {

    /**
     * SMS 메시지 등록 (NRMSG_DATA 테이블)
     * @param paramMap SMS 파라미터 (phone, callback, xmsText, xmsSubject, userId)
     * @return 삽입된 행 수
     */
    int insertSmsMessage(Map<String, Object> paramMap);

    /**
     * SMS 발송 상태 조회
     * @param paramMap 조회 조건 (msgKey)
     * @return 발송 상태 정보
     */
    Map<String, Object> selectSmsStatus(Map<String, Object> paramMap);
}
