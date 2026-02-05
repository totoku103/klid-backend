package com.klid.api.report.incident.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ReportInciPrcsStatService 통합 테스트
 */
class ReportInciPrcsStatServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportInciPrcsStatService")
    private ReportInciPrcsStatService reportInciPrcsStatService;

    @Test
    @DisplayName("getProcessStatusList - 미구현 상태 확인")
    void getProcessStatusList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciPrcsStatService.getProcessStatusList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("saveHighChartImage - 미구현 상태 확인")
    void saveHighChartImage_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciPrcsStatService.saveHighChartImage(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportReport - 미구현 상태 확인")
    void exportReport_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciPrcsStatService.exportReport(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
