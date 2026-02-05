package com.klid.api.system.code.controller;

import com.klid.api.system.code.dto.CodeRequest;
import com.klid.api.system.code.dto.CodeResponse;
import com.klid.api.system.code.dto.WeekDayRequest;
import com.klid.api.system.code.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 코드 관리 REST API
 * 공통코드, 지역코드, 기관코드 등의 관리 기능 제공
 */
@RestController("apiCodeController")
@RequiredArgsConstructor
@RequestMapping("/api/system/codes")
public class CodeController {

    private final CodeService codeService;

    /**
     * 코드 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<CodeResponse>> getCodeList(@RequestParam final Map<String, Object> params) {
        final List<CodeResponse> codes = codeService.getCodeList(params);
        return ResponseEntity.ok(codes);
    }

    /**
     * 코드 등록
     */
    @PostMapping
    public ResponseEntity<Void> addCode(@RequestBody final CodeRequest request) {
        codeService.addCode(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 코드 수정
     */
    @PutMapping("/{codeId}")
    public ResponseEntity<Void> editCode(
            @PathVariable final String codeId,
            @RequestBody final CodeRequest request) {
        codeService.editCode(codeId, request);
        return ResponseEntity.ok().build();
    }

    /**
     * 코드 중복 체크
     */
    @GetMapping("/check-duplicate")
    public ResponseEntity<Integer> checkCodeDuplicate(@RequestParam final Map<String, Object> params) {
        final Integer count = codeService.getCodeDuplCnt(params);
        return ResponseEntity.ok(count);
    }

    /**
     * 공휴일 등록
     */
    @PostMapping("/weekdays")
    public ResponseEntity<Void> addWeekDay(@RequestBody final WeekDayRequest request) {
        codeService.addWeekDay(request);
        return ResponseEntity.ok().build();
    }

    /**
     * 공휴일 삭제
     */
    @DeleteMapping("/weekdays/{weekdayId}")
    public ResponseEntity<Void> deleteWeekDay(@PathVariable final String weekdayId) {
        codeService.delWeekDay(weekdayId);
        return ResponseEntity.ok().build();
    }
}
