package com.klid.webapp.realtimeNotify.sms.controller;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.sms.service.RealtimeNotifySmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * RealtimeNotify SMS Controller
 * SMS 발송 REST API 엔드포인트
 */
@Slf4j
@RestController
@RequestMapping("/api/realtime-notify/sms")
@RequiredArgsConstructor
public class RealtimeNotifySmsController {

    private final RealtimeNotifySmsService realtimeNotifySmsService;

    /**
     * SMS 발송
     * @param reqMap 발송 요청 파라미터
     *   - msg: 메시지 내용
     *   - subject: 메시지 제목 (선택)
     *   - sender: 발신자 번호
     *   - recv: 수신자 목록 [{userName, phone}, ...]
     *   - userId: 발송자 ID (선택)
     * @return 발송 결과
     */
    @PostMapping("/send")
    public ResponseEntity<ReturnData> sendSms(@RequestBody Map<String, Object> reqMap) {
        log.info("SMS 발송 요청: sender={}", reqMap.get("sender"));
        ReturnData result = realtimeNotifySmsService.sendSms(new Criterion(reqMap, false));
        return toResponseEntity(result, HttpStatus.OK);
    }

    /**
     * SMS 발송 상태 조회
     * @param reqMap 조회 조건 (msgKey)
     * @return 발송 상태
     */
    @GetMapping("/status")
    public ResponseEntity<ReturnData> getSmsStatus(@RequestParam Map<String, Object> reqMap) {
        ReturnData result = realtimeNotifySmsService.getSmsStatus(new Criterion(reqMap));
        return toResponseEntity(result, HttpStatus.OK);
    }

    private ResponseEntity<ReturnData> toResponseEntity(ReturnData result, HttpStatus successStatus) {
        if (result.getHasError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.status(successStatus).body(result);
    }
}
