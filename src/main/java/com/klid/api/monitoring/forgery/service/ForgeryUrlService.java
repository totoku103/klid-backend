package com.klid.api.monitoring.forgery.service;

import com.klid.api.monitoring.forgery.dto.ForgeryUrlDTO;
import com.klid.api.monitoring.forgery.persistence.ForgeryUrlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 위변조 URL Service
 */
@Service
@RequiredArgsConstructor
public class ForgeryUrlService {

    private final ForgeryUrlMapper forgeryUrlMapper;

    /**
     * 위변조 URL 목록 조회
     */
    public List<Map<String, Object>> getForgeryUrl(Map<String, Object> params) {
        return forgeryUrlMapper.selectForgeryUrl(params);
    }

    /**
     * 위변조 URL 이력 목록 조회
     */
    public List<Map<String, Object>> getForgeryUrlHist(Map<String, Object> params) {
        return forgeryUrlMapper.selectForgeryUrlHist(params);
    }

    /**
     * 메인 홈페이지 모니터링 조회
     */
    public List<ForgeryUrlDTO> getMainForgeryHm(Map<String, Object> params) {
        return forgeryUrlMapper.selectMainForgeryHm(params);
    }

    /**
     * 메인 홈페이지 모니터링 수치 통계
     */
    public Map<String, Object> getMainForgeryCnt(Map<String, Object> params) {
        final Map<String, Object> result = new HashMap<>();
        result.put("contents", forgeryUrlMapper.selectMainForgeryCnt(params));
        return result;
    }

    /**
     * 기관명으로 조회
     */
    public ForgeryUrlDTO getByInstNm(Map<String, Object> params) {
        return forgeryUrlMapper.getByInstNm(params);
    }
}
