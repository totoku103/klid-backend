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
 * ReportInciAttNatnService 통합 테스트
 */
class ReportInciAttNatnServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportInciAttNatnService")
    private ReportInciAttNatnService reportInciAttNatnService;

    @Test
    @DisplayName("getAttackNationList - 미구현 상태 확인")
    void getAttackNationList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciAttNatnService.getAttackNationList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("saveHighChartImage - 미구현 상태 확인")
    void saveHighChartImage_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciAttNatnService.saveHighChartImage(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportReport - 미구현 상태 확인")
    void exportReport_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciAttNatnService.exportReport(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
