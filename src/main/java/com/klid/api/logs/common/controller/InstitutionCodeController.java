package com.klid.api.logs.common.controller;

import com.klid.api.logs.common.dto.InstitutionCodeResDTO;
import com.klid.api.logs.common.dto.ThirdPartySystemType;
import com.klid.api.logs.common.service.InstitutionCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs/institution-code")
public class InstitutionCodeController {

    private final InstitutionCodeService institutionCodeService;

    @GetMapping("/list")
    public ResponseEntity<List<InstitutionCodeResDTO>> getInstitutionCodeList(
            @RequestParam final ThirdPartySystemType systemType) {
        log.debug("Inside getInstitutionCodeList. {}", systemType);

        final List<InstitutionCodeResDTO> otherInstitutionList;
        switch (systemType) {
            case CTRS:
                otherInstitutionList = institutionCodeService.getCtrsInstitutionCodeList();
                break;
            case CTSS:
            case VMS:
                otherInstitutionList = institutionCodeService.getOtherInstitutionCodeList(systemType);
                break;
            default:
                throw new IllegalArgumentException("Invalid system type: " + systemType);
        }

        return ResponseEntity.ok(otherInstitutionList);
    }
}
