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
 * ReportInciTypeService 통합 테스트
 */
class ReportInciTypeServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportInciTypeService")
    private ReportInciTypeService reportInciTypeService;

    @Test
    @DisplayName("getTypeList - 미구현 상태 확인")
    void getTypeList_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciTypeService.getTypeList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("saveHighChartImage - 미구현 상태 확인")
    void saveHighChartImage_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciTypeService.saveHighChartImage(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportReport - 미구현 상태 확인")
    void exportReport_shouldThrowUnsupportedOperationException() {
        final Map<String, Object> params = new HashMap<>();

        assertThatThrownBy(() -> reportInciTypeService.exportReport(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
