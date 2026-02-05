package com.klid.api.report.security.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * ReportSecurityResultService 통합 테스트
 *
 * Note: 서비스 메소드들이 UnsupportedOperationException을 던지므로
 * 현재는 예외 발생 여부만 확인
 */
class ReportSecurityResultServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiReportSecurityResultService")
    private ReportSecurityResultService reportSecurityResultService;

    @Test
    @DisplayName("getResultTotal - 미구현 상태 확인")
    void getResultTotal_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportSecurityResultService.getResultTotal(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getResultList - 미구현 상태 확인")
    void getResultList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportSecurityResultService.getResultList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("getResultExceptList - 미구현 상태 확인")
    void getResultExceptList_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportSecurityResultService.getResultExceptList(params))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }

    @Test
    @DisplayName("makeReportDownload - 미구현 상태 확인")
    void makeReportDownload_shouldThrowUnsupportedOperationException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> reportSecurityResultService.makeReportDownload(params, null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Not implemented yet");
    }
}
