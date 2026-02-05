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
 * ReportInciLocalService 통합 테스트
 */
class ReportInciLocalServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportInciLocalService")
    private ReportInciLocalService reportInciLocalService;

    @Test
    @DisplayName("getLocalList - 미구현 상태 확인")
    void getLocalList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciLocalService.getLocalList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getSidoList - 미구현 상태 확인")
    void getSidoList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciLocalService.getSidoList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("saveHighChartImage - 미구현 상태 확인")
    void saveHighChartImage_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciLocalService.saveHighChartImage(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportReport - 미구현 상태 확인")
    void exportReport_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciLocalService.exportReport(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
