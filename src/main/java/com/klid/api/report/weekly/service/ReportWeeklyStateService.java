package com.klid.api.report.weekly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 주간 상태 보고서 Service
 */
@Service("apiReportWeeklyStateService")
@RequiredArgsConstructor
public class ReportWeeklyStateService {

    // TODO: Mapper 주입 필요
    // private final ReportWeeklyStateMapper mapper;
    // private final NoticeBoardMapper noticeBoardMapper;
    // private final ReportDailyStateMapper reportDailyStateMapper;

    public Map<String, Object> getRotationList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getWeeklyList(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getWeeklyListBefore(final Map<String, Object> params) {
        // TODO: 원본 서비스 로직 마이그레이션 필요
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Map<String, Object> getWeeklyTotalList(final Map<String, Object> params) {
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
        // 원본: HwpmlMaker를 사용한 매우 복잡한 한글 문서 생성
        // - 공지사항 목록 조회 및 포함
        // - NCSC 목록 조회
        // - 기관명 조회
        // - 날짜 계산 및 포맷팅
        // - 이번 주/지난 주 비교 데이터
        // - 다양한 통계 데이터 포맷팅
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
