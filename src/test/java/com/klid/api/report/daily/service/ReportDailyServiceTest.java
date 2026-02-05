package com.klid.api.report.daily.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ReportDailyService 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 예외 발생 여부만 확인
 */
class ReportDailyServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportDailyService")
    private ReportDailyService reportDailyService;

    @Test
    @DisplayName("getDayStatistics - 미구현 상태 확인")
    void getDayStatistics_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyService.getDayStatistics(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("downloadDailyReport - 미구현 상태 확인")
    void downloadDailyReport_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyService.downloadDailyReport(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
