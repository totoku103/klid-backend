package com.klid.api.report.incident.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 사고 지역별 보고서 Service
 */
@Service
@RequiredArgsConstructor
public class ReportInciLocalService {

    // TODO: Mapper 주입 필요
    // private final ReportInciLocalMapper mapper;

    public Map<String, Object> getLocalList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getSidoList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> saveHighChartImage(final Map<String, Object> params) {
        // TODO: Base64 이미지 저장 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportReport(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 보고서 생성 로직 마이그레이션 필요
        // 시도/시군구 구분에 따른 보고서 타입 처리
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
