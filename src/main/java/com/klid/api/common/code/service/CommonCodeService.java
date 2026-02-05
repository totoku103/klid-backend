package com.klid.api.common.code.service;

import com.klid.api.common.code.dto.CommonCodeDTO;
import com.klid.api.common.code.persistence.CommonCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonCodeService {

    private final CommonCodeMapper commonCodeMapper;

    public List<CommonCodeDTO> getCommonCodeList(
            final String comCode1,
            final String comCode2,
            final String comCode3,
            final Integer codeLvl) {
        return commonCodeMapper.selectCommonCode(comCode1, comCode2, comCode3, codeLvl);
    }
}
