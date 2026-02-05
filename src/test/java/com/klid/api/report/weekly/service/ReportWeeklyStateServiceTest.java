package com.klid.api.report.weekly.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ReportWeeklyStateService 통합 테스트
 */
class ReportWeeklyStateServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportWeeklyStateService")
    private ReportWeeklyStateService reportWeeklyStateService;

    @Test
    @DisplayName("getRotationList - 미구현 상태 확인")
    void getRotationList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.getRotationList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getWeeklyList - 미구현 상태 확인")
    void getWeeklyList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.getWeeklyList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getWeeklyListBefore - 미구현 상태 확인")
    void getWeeklyListBefore_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.getWeeklyListBefore(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getWeeklyTotalList - 미구현 상태 확인")
    void getWeeklyTotalList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.getWeeklyTotalList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getTypeAccidentList - 미구현 상태 확인")
    void getTypeAccidentList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.getTypeAccidentList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getDetectionList - 미구현 상태 확인")
    void getDetectionList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.getDetectionList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("makeReportDownload - 미구현 상태 확인")
    void makeReportDownload_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportWeeklyStateService.makeReportDownload(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
