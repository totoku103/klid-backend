package com.klid.api.report.daily.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 일일 상태 보고서 Service
 */
@Service("apiReportDailyStateService")
@RequiredArgsConstructor
public class ReportDailyStateService {

    // TODO: Mapper 주입 필요
    // private final ReportDailyStateMapper mapper;
    // private final NoticeBoardMapper noticeBoardMapper;

    public Map<String, Object> getRotationList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getDailyList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getDailyTotalList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getTypeAccidentList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getDetectionList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> makeReportDownload(final Map<String, Object> params, final HttpServletResponse response) {
        // TODO: HWP 보고서 생성 로직 마이그레이션 필요
        // 원본: HwpmlMaker를 사용한 복잡한 한글 문서 생성
        // - 공지사항 목록 조회 및 포함
        // - 기관명 조회
        // - 다양한 통계 데이터 포맷팅
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
