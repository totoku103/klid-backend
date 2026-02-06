package com.klid.api.report.incident.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 사고 상세 현황 보고서 Service
 */
@Service
@RequiredArgsConstructor
public class ReportInciDetailService {

    // TODO: Mapper 주입 필요
    // private final ReportInciDetailMapper mapper;

    public Map<String, Object> getDetailList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> exportDailyReport(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 파일 생성 및 다운로드 로직 마이그레이션 필요
        // 원본: HWPReader, HWPWriter를 사용한 복잡한 한글 문서 생성
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
