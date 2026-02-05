package com.klid.api.webdash.sido.service;

import com.klid.api.BaseServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;

/**
 * WebDashSidoService 통합 테스트
 */
class WebDashSidoServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiWebDashSidoService")
    private WebDashSidoService webDashSidoService;

    @Test
    @DisplayName("getNoticeList - 공지사항 목록 조회")
    void getNoticeList_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getNoticeList(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getSecuList - 보안자료 목록 조회")
    void getSecuList_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getSecuList(params);

        // then
    }

    @Test
    @DisplayName("getRegionStatusManual - 수동 지역 현황 조회")
    void getRegionStatusManual_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getRegionStatusManual(params);

        // then
    }

    @Test
    @DisplayName("getForgeryCheck - 위변조 체크 조회")
    void getForgeryCheck_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getForgeryCheck(params);

        // then
    }

    @Test
    @DisplayName("getHcCheck - 헬스체크 조회")
    void getHcCheck_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getHcCheck(params);

        // then
    }

    @Test
    @DisplayName("getProcess - 처리 현황 조회")
    void getProcess_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getProcess(params);

        // then
    }

    @Test
    @DisplayName("getSidoList - 시도 목록 조회")
    void getSidoList_shouldReturnData() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final Object result = webDashSidoService.getSidoList(params);

        // then
    }
}
