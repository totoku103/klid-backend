package com.klid.api.webdash.admin.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.api.webdash.admin.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AdminControlMapper 통합 테스트
 */
class AdminControlMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiAdminControlMapper")
    private AdminControlMapper adminControlMapper;

    @Test
    @DisplayName("selectIncidentStatus - 사고 현황 조회")
    void selectIncidentStatus_shouldReturnStatus() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final IncidentDTO result = adminControlMapper.selectIncidentStatus(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("selectInciCnt - 사고 건수 조회")
    void selectInciCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<String> result = adminControlMapper.selectInciCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectTbzledgeCnt - Tbzledge 건수 조회")
    void selectTbzledgeCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<TbzledgeCntDTO> result = adminControlMapper.selectTbzledgeCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectLocalInciCnt - 지역별 사고 건수 조회")
    void selectLocalInciCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<String> result = adminControlMapper.selectLocalInciCnt(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectLocalStatus - 지역별 현황 조회")
    void selectLocalStatus_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<LocalStatusDTO> result = adminControlMapper.selectLocalStatus(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectUrlStatus - URL 현황 조회")
    void selectUrlStatus_shouldReturnStatus() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final UrlStatusDTO result = adminControlMapper.selectUrlStatus(params);

        // then - 데이터 유무에 관계없이 예외 없이 실행
    }

    @Test
    @DisplayName("selectSysErrorStatus - hostNm이 local일 때 시스템 오류 현황 조회")
    void selectSysErrorStatus_withLocalHostNm_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("hostNm", "local");

        // when
        final List<SysErrorDTO> result = adminControlMapper.selectSysErrorStatus(params);

        // then
        assertThat(result).isNotNull();
    }


    @Test
    @DisplayName("selectInciTypeCnt - 사고 유형별 건수 조회")
    void selectInciTypeCnt_shouldReturnList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("atype", 1);

        // when
        final List<String> result = adminControlMapper.selectInciTypeCnt(params);

        // then
        assertThat(result).isNotNull();
    }
}
