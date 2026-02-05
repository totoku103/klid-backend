package com.klid.webapp.realtimeNotify.sms.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;

/**
 * RealtimeNotify SMS Service Interface
 */
public interface RealtimeNotifySmsService {

    /**
     * SMS 발송
     * @param criterion 발송 파라미터
     * @return 발송 결과
     */
    ReturnData sendSms(Criterion criterion);

    /**
     * SMS 발송 상태 조회
     * @param criterion 조회 조건
     * @return 발송 상태
     */
    ReturnData getSmsStatus(Criterion criterion);
}
