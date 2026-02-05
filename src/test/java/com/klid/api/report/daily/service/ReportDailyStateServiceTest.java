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
 * ReportDailyStateService 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 예외 발생 여부만 확인
 */
class ReportDailyStateServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportDailyStateService")
    private ReportDailyStateService reportDailyStateService;

    @Test
    @DisplayName("getRotationList - 미구현 상태 확인")
    void getRotationList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyStateService.getRotationList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getDailyList - 미구현 상태 확인")
    void getDailyList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyStateService.getDailyList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getDailyTotalList - 미구현 상태 확인")
    void getDailyTotalList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyStateService.getDailyTotalList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getTypeAccidentList - 미구현 상태 확인")
    void getTypeAccidentList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyStateService.getTypeAccidentList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getDetectionList - 미구현 상태 확인")
    void getDetectionList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyStateService.getDetectionList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("makeReportDownload - 미구현 상태 확인")
    void makeReportDownload_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportDailyStateService.makeReportDownload(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
