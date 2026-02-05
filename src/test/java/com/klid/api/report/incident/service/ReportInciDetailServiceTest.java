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
 * ReportInciDetailService 통합 테스트
 */
class ReportInciDetailServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportInciDetailService")
    private ReportInciDetailService reportInciDetailService;

    @Test
    @DisplayName("getDetailList - 미구현 상태 확인")
    void getDetailList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciDetailService.getDetailList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportDailyReport - 미구현 상태 확인")
    void exportDailyReport_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciDetailService.exportDailyReport(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
