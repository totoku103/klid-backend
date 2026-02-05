package com.klid.api.report.collection.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 보고서 수집 현황 Service
 */
@Service("apiReportCollectionService")
@RequiredArgsConstructor
public class ReportCollectionService {

    // TODO: Mapper 주입 필요
    // private final ReportCollectionMapper mapper;

    public Map<String, Object> getSecurityHackingDetail(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getSecurityListDetail(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getNoticeListDetail(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getSecurityVulnerabilityDetail(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getIncidentDetail(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportNoticeList(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: 엑셀 출력 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportSecurityList(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: 엑셀 출력 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportSecurityHacking(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: 엑셀 출력 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportSecurityVulnerability(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: 엑셀 출력 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportIncidentDetail(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: 엑셀 출력 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportCTRSDaily(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: 엑셀 출력 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
