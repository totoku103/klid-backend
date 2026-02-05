package com.klid.api.system.sms.controller;

import com.klid.api.system.sms.dto.SmsMessageRequest;
import com.klid.api.system.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * SMS 전송 REST API
 */
@RestController("apiSmsController")
@RequiredArgsConstructor
@RequestMapping("/api/system/sms")
public class SmsController {

    private final SmsService smsService;

    /**
     * SMS 메시지 전송 (Nuri2 연동)
     */
    @PostMapping("/send")
    public ResponseEntity<Void> sendSmsMessage(@RequestBody final SmsMessageRequest request) {
        smsService.sendSmsMessage(request);
        return ResponseEntity.ok().build();
    }
}
