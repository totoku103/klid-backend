package com.klid.api.webdash.admin.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.webdash.admin.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * AdminControlService 통합 테스트
 */
class AdminControlServiceTest extends BaseServiceTest {

    @Autowired
    @Qualifier("apiAdminControlService")
    private AdminControlService adminControlService;

    @Test
    @DisplayName("getIncidentStatus - 사고 현황 조회")
    void getIncidentStatus_shouldReturnStatus() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final IncidentDTO result = adminControlService.getIncidentStatus(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getInciCnt - 사고 건수 조회")
    void getInciCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<InciCntDTO> result = adminControlService.getInciCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getTbzledgeCnt - Tbzledge 건수 조회")
    void getTbzledgeCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<TbzledgeCntDTO> result = adminControlService.getTbzledgeCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getLocalInciCnt - 지역별 사고 건수 조회")
    void getLocalInciCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<InciCntDTO> result = adminControlService.getLocalInciCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getLocalInciCnt - AUTH_MAIN_2로 지역별 사고 건수 조회")
    void getLocalInciCnt_withAuthMain2_shouldReturnListWithAllRegions() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);
        params.put("sAuthMain", "AUTH_MAIN_2");

        // when
        final List<InciCntDTO> result = adminControlService.getLocalInciCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getLocalStatus - 지역별 현황 조회")
    void getLocalStatus_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<LocalStatusDTO> result = adminControlService.getLocalStatus(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("getUrlStatus - URL 현황 조회")
    void getUrlStatus_shouldReturnStatus() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final UrlStatusDTO result = adminControlService.getUrlStatus(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("getSysErrorStatus - hostNm이 null이면 IllegalArgumentException 발생")
    void getSysErrorStatus_withoutHostNm_shouldThrowException() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when & then
        assertThatThrownBy(() -> adminControlService.getSysErrorStatus(params))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("hostNm 파라미터는 필수입니다");
    }

    @Test
    @DisplayName("getSysErrorStatus - hostNm이 빈 문자열이면 IllegalArgumentException 발생")
    void getSysErrorStatus_withEmptyHostNm_shouldThrowException() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("hostNm", "");

        // when & then
        assertThatThrownBy(() -> adminControlService.getSysErrorStatus(params))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("hostNm 파라미터는 필수입니다");
    }

    @Test
    @DisplayName("getSysErrorStatus - hostNm이 local이면 시스템 오류 현황 조회")
    void getSysErrorStatus_withLocalHostNm_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("hostNm", "local");

        // when
        final List<SysErrorDTO> result = adminControlService.getSysErrorStatus(params);

        // then
        assertThat(result).isNotNull();
    }


    @Test
    @DisplayName("getInciTypeCnt - 사고 유형별 건수 조회")
    void getInciTypeCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<String> result = adminControlService.getInciTypeCnt(params);

        // then
        assertThat(result).isNotNull();
    }
}
