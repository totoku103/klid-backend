package com.klid.api.report.daily.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 일일 사고 현황 보고서 Service
 */
@Service
@RequiredArgsConstructor
public class ReportDailyInciStateService {

    // TODO: Mapper 주입 필요
    // private final ReportDailyInciStateMapper mapper;

    public Map<String, Object> getDailyList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getDailyTotalList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> saveHighChartImage(final Map<String, Object> params) {
        // TODO: Base64 이미지 저장 로직 마이그레이션 필요
        // 원본: Apache Commons Codec Base64 디코딩 후 파일 저장
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> makeReportDownload(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 보고서 생성 로직 마이그레이션 필요
        // 원본: HwpmlMaker를 사용한 한글 문서 생성
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
