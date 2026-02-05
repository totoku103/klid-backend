package com.klid.api.common.code.controller;

import com.klid.api.common.code.dto.CommonCodeDTO;
import com.klid.api.common.code.service.CommonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common-codes")
public class CommonCodeController {

    private final CommonCodeService commonCodeService;

    @GetMapping
    public ResponseEntity<List<CommonCodeDTO>> getCommonCodes(
            @RequestParam(required = false) final String comCode1,
            @RequestParam(required = false) final String comCode2,
            @RequestParam(required = false) final String comCode3,
            @RequestParam(required = false) final Integer codeLvl) {
        final List<CommonCodeDTO> result = commonCodeService.getCommonCodeList(
                comCode1, comCode2, comCode3, codeLvl);
        return ResponseEntity.ok(result);
    }
}
