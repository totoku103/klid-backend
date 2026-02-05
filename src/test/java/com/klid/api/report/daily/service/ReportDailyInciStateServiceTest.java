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
 * ReportDailyInciStateService 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 예외 발생 여부만 확인
 */
class ReportDailyInciStateServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportDailyInciStateService")
    private ReportDailyInciStateService reportDailyInciStateService;

    @Test
    @DisplayName("getDailyList - 미구현 상태 확인")
    void getDailyList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyInciStateService.getDailyList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getDailyTotalList - 미구현 상태 확인")
    void getDailyTotalList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyInciStateService.getDailyTotalList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("saveHighChartImage - 미구현 상태 확인")
    void saveHighChartImage_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyInciStateService.saveHighChartImage(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("makeReportDownload - 미구현 상태 확인")
    void makeReportDownload_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyInciStateService.makeReportDownload(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
