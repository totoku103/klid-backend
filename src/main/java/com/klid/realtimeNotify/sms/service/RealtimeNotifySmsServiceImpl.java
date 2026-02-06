package com.klid.realtimeNotify.sms.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ErrorInfo;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.realtimeNotify.sms.dto.SmsSendResponseDto;
import com.klid.realtimeNotify.sms.persistence.RealtimeNotifySmsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RealtimeNotify SMS Service Implementation
 * NRMSG_DATA 테이블에 직접 INSERT하여 SMS 게이트웨이 연동
 */
@Slf4j
@Service("realtimeNotifySmsService")
public class RealtimeNotifySmsServiceImpl extends MsgService implements RealtimeNotifySmsService {

    private final RealtimeNotifySmsMapper mapper;

    public RealtimeNotifySmsServiceImpl(MessageSource messageSource, RealtimeNotifySmsMapper mapper) {
        super(messageSource);
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ReturnData sendSms(Criterion criterion) {
        ReturnData returnData = new ReturnData();

        try {
            String msg = criterion.getValue("msg").toString();
            String subject = criterion.getValue("subject") != null ?
                criterion.getValue("subject").toString() : "";
            String sender = criterion.getValue("sender").toString();
            String userId = criterion.getValue("userId") != null ?
                criterion.getValue("userId").toString() : "";

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> receivers = (List<Map<String, Object>>) criterion.getValue("recv");

            if (receivers == null || receivers.isEmpty()) {
                returnData.setErrorInfo(new ErrorInfo("ERR_NO_RECEIVER", "수신자가 없습니다."));
                return returnData;
            }

            int successCount = 0;

            for (Map<String, Object> receiver : receivers) {
                String phone = receiver.get("phone").toString();
                // 전화번호에서 하이픈 제거
                phone = phone.replaceAll("-", "");

                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("phone", phone);
                paramMap.put("callback", sender.replaceAll("-", ""));
                paramMap.put("xmsText", msg);
                paramMap.put("xmsSubject", subject);
                paramMap.put("userId", userId);

                int result = mapper.insertSmsMessage(paramMap);
                if (result > 0) {
                    successCount++;
                }
            }

            SmsSendResponseDto response = SmsSendResponseDto.builder()
                    .success(successCount > 0)
                    .sentCount(successCount)
                    .message(successCount + "건의 SMS가 발송 대기열에 등록되었습니다.")
                    .build();

            returnData.setResultData(response);
            log.info("SMS 발송 요청 완료: {}건", successCount);

        } catch (Exception e) {
            log.error("SMS 발송 중 오류 발생", e);
            returnData.setErrorInfo(new ErrorInfo("ERR_SMS_SEND", "SMS 발송 중 오류가 발생했습니다: " + e.getMessage()));
        }

        return returnData;
    }

    @Override
    public ReturnData getSmsStatus(Criterion criterion) {
        ReturnData returnData = new ReturnData();

        try {
            Map<String, Object> status = mapper.selectSmsStatus(criterion.getCondition());
            returnData.setResultData(status);
        } catch (Exception e) {
            log.error("SMS 상태 조회 중 오류 발생", e);
            returnData.setErrorInfo(new ErrorInfo("ERR_SMS_STATUS", "SMS 상태 조회 중 오류가 발생했습니다."));
        }

        return returnData;
    }
}
