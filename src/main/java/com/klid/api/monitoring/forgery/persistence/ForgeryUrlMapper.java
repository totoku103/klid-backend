package com.klid.api.monitoring.forgery.persistence;

import com.klid.api.monitoring.forgery.dto.ForgeryUrlDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 위변조 URL Mapper
 */
@Component("apiForgeryUrlMapper")
@Mapper
public interface ForgeryUrlMapper {

    /**
     * 위변조 URL 목록 조회
     */
    List<Map<String, Object>> selectForgeryUrl(Map<String, Object> params);

    /**
     * 위변조 URL 이력 목록 조회
     */
    List<Map<String, Object>> selectForgeryUrlHist(Map<String, Object> params);

    /**
     * 메인 홈페이지 모니터링 조회
     */
    List<ForgeryUrlDTO> selectMainForgeryHm(Map<String, Object> params);

    /**
     * 메인 홈페이지 모니터링 수치 통계
     */
    ForgeryUrlDTO selectMainForgeryCnt(Map<String, Object> params);

    /**
     * 기관명으로 조회
     */
    ForgeryUrlDTO getByInstNm(Map<String, Object> params);
}
