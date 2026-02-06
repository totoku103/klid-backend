package com.klid.api.report.security.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 보안 결과 보고서 Service
 */
@Service("apiReportSecurityResultService")
@RequiredArgsConstructor
public class ReportSecurityResultService {

    // TODO: Mapper 주입 필요
    // private final ReportSecurityResultMapper mapper;

    public Map<String, Object> getResultTotal(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getResultList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getResultExceptList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> makeReportDownload(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 보고서 생성 로직 마이그레이션 필요
        // 원본: HwpmlMaker를 사용한 매우 복잡한 한글 문서 생성
        // - 보안 타이틀 목록 조회 및 포함
        // - 동적 행 추가 (예외 목록 크기에 따라)
        // - 다양한 보안 통계 데이터 포맷팅
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
