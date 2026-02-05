package com.klid.api.report.daily.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 일일 보고서 Service
 */
@Service("apiReportDailyService")
@RequiredArgsConstructor
public class ReportDailyService {

    // TODO: Mapper 주입 필요
    // private final ReportDailyMapper mapper;

    public Map<String, Object> getDayStatistics(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> downloadDailyReport(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 파일 생성 로직 마이그레이션 필요
        // 원본: HWPReader, HWPWriter를 사용한 복잡한 한글 문서 생성 로직
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
