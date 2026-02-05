package com.klid.webapp.realtimeNotify.notification.controller;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.realtimeNotify.notification.service.RealtimeNotifyNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * RealtimeNotify Notification REST Controller
 * 알림 발송 REST API 엔드포인트
 */
@Slf4j
@RestController
@RequestMapping("/api/realtime-notify/notification")
@RequiredArgsConstructor
public class RealtimeNotifyNotificationController {

    private final RealtimeNotifyNotificationService realtimeNotifyNotificationService;

    /**
     * 알림 발송
     * @param reqMap 알림 요청 파라미터
     *   - type: 알림 타입 (ACCIDENT_RECEIVED, ACCIDENT_UPDATED, etc.)
     *   - title: 알림 제목
     *   - message: 알림 메시지
     *   - userId: 대상 사용자 ID (개인 알림용)
     *   - deptCode: 대상 기관 코드 (기관 알림용)
     *   - data: 관련 데이터
     *   - broadcast: 브로드캐스트 여부 (전체 알림)
     * @return 발송 결과
     */
    @PostMapping("/send")
    public ResponseEntity<ReturnData> sendNotification(@RequestBody Map<String, Object> reqMap) {
        log.info("알림 발송 요청: type={}, broadcast={}", reqMap.get("type"), reqMap.get("broadcast"));
        ReturnData result = realtimeNotifyNotificationService.sendNotification(new Criterion(reqMap, false));
        return toResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 브로드캐스트 알림 발송 (전체)
     * @param reqMap 알림 요청 파라미터
     * @return 발송 결과
     */
    @PostMapping("/broadcast")
    public ResponseEntity<ReturnData> broadcastNotification(@RequestBody Map<String, Object> reqMap) {
        reqMap.put("broadcast", true);
        log.info("브로드캐스트 알림 발송 요청: type={}", reqMap.get("type"));
        ReturnData result = realtimeNotifyNotificationService.sendNotification(new Criterion(reqMap, false));
        return toResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 기관별 알림 발송
     * @param deptCode 기관 코드
     * @param reqMap 알림 요청 파라미터
     * @return 발송 결과
     */
    @PostMapping("/department/{deptCode}")
    public ResponseEntity<ReturnData> sendToDepartment(
            @PathVariable("deptCode") String deptCode,
            @RequestBody Map<String, Object> reqMap) {
        reqMap.put("deptCode", deptCode);
        log.info("기관 알림 발송 요청: deptCode={}, type={}", deptCode, reqMap.get("type"));
        ReturnData result = realtimeNotifyNotificationService.sendNotification(new Criterion(reqMap, false));
        return toResponseEntity(result, HttpStatus.OK);
    }

    /**
     * 사용자별 알림 발송
     * @param userId 사용자 ID
     * @param reqMap 알림 요청 파라미터
     * @return 발송 결과
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<ReturnData> sendToUser(
            @PathVariable("userId") String userId,
            @RequestBody Map<String, Object> reqMap) {
        reqMap.put("userId", userId);
        log.info("사용자 알림 발송 요청: userId={}, type={}", userId, reqMap.get("type"));
        ReturnData result = realtimeNotifyNotificationService.sendNotification(new Criterion(reqMap, false));
        return toResponseEntity(result, HttpStatus.OK);
    }

    private ResponseEntity<ReturnData> toResponseEntity(ReturnData result, HttpStatus successStatus) {
        if (result.getHasError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.status(successStatus).body(result);
    }
}
