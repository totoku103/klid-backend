package com.klid.api.logs.common.service;

import com.klid.api.logs.common.dto.InstitutionCodeResDTO;
import com.klid.api.logs.common.dto.ThirdPartySystemType;
import com.klid.api.logs.common.persistence.InstitutionCodeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstitutionCodeService {

    private final InstitutionCodeMapper institutionCodeMapper;

    public List<InstitutionCodeResDTO> getCtrsInstitutionCodeList() {
        final List<InstitutionCodeResDTO> institutionCodeResDTOS = institutionCodeMapper.selectCTRSInstitutionCodeList();
        log.debug("getCtrsInstitutionCodeList:InstitutionCodeResDTO: {}", institutionCodeResDTOS.size());
        return institutionCodeResDTOS;
    }

    public List<InstitutionCodeResDTO> getOtherInstitutionCodeList(final ThirdPartySystemType systemType) {
        final List<InstitutionCodeResDTO> institutionCodeResDTOS =
            institutionCodeMapper.selectOtherInstitutionCodeList(systemType.getValue().toUpperCase());
        log.debug("getOtherInstitutionCodeList:InstitutionCodeResDTO: {}", institutionCodeResDTOS.size());
        return institutionCodeResDTOS;
    }
}
