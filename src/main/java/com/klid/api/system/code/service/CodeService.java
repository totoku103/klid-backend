package com.klid.api.system.code.service;

import com.klid.api.system.code.dto.CodeRequest;
import com.klid.api.system.code.dto.CodeResponse;
import com.klid.api.system.code.dto.WeekDayRequest;
import com.klid.api.system.code.persistence.CodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 코드 관리 서비스
 */
@Service("systemCodeService")
@RequiredArgsConstructor
public class CodeService {

    private final CodeMapper codeMapper;

    /**
     * 코드 목록 조회
     */
    public List<CodeResponse> getCodeList(final Map<String, Object> params) {
        return codeMapper.selectCodeList(params);
    }

    /**
     * 코드 등록
     */
    @Transactional
    public void addCode(final CodeRequest request) {
        codeMapper.insertCode(request);
    }

    /**
     * 코드 수정
     */
    @Transactional
    public void editCode(final String codeId, final CodeRequest request) {
        request.setCodeId(codeId);
        codeMapper.updateCode(request);
    }

    /**
     * 코드 중복 체크
     */
    public Integer getCodeDuplCnt(final Map<String, Object> params) {
        return codeMapper.selectCodeDuplCnt(params);
    }

    /**
     * 공휴일 등록
     */
    @Transactional
    public void addWeekDay(final WeekDayRequest request) {
        codeMapper.insertWeekDay(request);
    }

    /**
     * 공휴일 삭제
     */
    @Transactional
    public void delWeekDay(final String weekdayId) {
        codeMapper.deleteWeekDay(weekdayId);
    }
}
