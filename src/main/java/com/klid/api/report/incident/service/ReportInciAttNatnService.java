package com.klid.api.report.incident.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 사고 공격국가별 보고서 Service
 */
@Service("apiReportInciAttNatnService")
@RequiredArgsConstructor
public class ReportInciAttNatnService {

    // TODO: Mapper 주입 필요
    // private final ReportInciAttNatnMapper mapper;

    public Map<String, Object> getAttackNationList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> saveHighChartImage(final Map<String, Object> params) {
        // TODO: Base64 이미지 저장 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportReport(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 보고서 생성 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
