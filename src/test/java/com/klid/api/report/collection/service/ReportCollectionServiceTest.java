package com.klid.api.report.collection.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ReportCollectionService 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 예외 발생 여부만 확인
 */
class ReportCollectionServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportCollectionService")
    private ReportCollectionService reportCollectionService;

    @Test
    @DisplayName("getSecurityHackingDetail - 미구현 상태 확인")
    void getSecurityHackingDetail_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.getSecurityHackingDetail(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getSecurityListDetail - 미구현 상태 확인")
    void getSecurityListDetail_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.getSecurityListDetail(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getNoticeListDetail - 미구현 상태 확인")
    void getNoticeListDetail_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.getNoticeListDetail(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getSecurityVulnerabilityDetail - 미구현 상태 확인")
    void getSecurityVulnerabilityDetail_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.getSecurityVulnerabilityDetail(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getIncidentDetail - 미구현 상태 확인")
    void getIncidentDetail_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.getIncidentDetail(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportNoticeList - 미구현 상태 확인")
    void exportNoticeList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.exportNoticeList(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportSecurityList - 미구현 상태 확인")
    void exportSecurityList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.exportSecurityList(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportSecurityHacking - 미구현 상태 확인")
    void exportSecurityHacking_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.exportSecurityHacking(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportSecurityVulnerability - 미구현 상태 확인")
    void exportSecurityVulnerability_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.exportSecurityVulnerability(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportIncidentDetail - 미구현 상태 확인")
    void exportIncidentDetail_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.exportIncidentDetail(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("exportCTRSDaily - 미구현 상태 확인")
    void exportCTRSDaily_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportCollectionService.exportCTRSDaily(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
