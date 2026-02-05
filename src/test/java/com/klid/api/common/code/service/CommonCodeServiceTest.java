package com.klid.api.common.code.service;

import com.klid.api.BaseServiceTest;
import com.klid.api.common.code.dto.CommonCodeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 공통 코드 Service 통합 테스트
 */
@DisplayName("공통 코드 Service 테스트")
class CommonCodeServiceTest extends BaseServiceTest {

    @Autowired
    private CommonCodeService commonCodeService;

    @Test
    @DisplayName("공통 코드 목록 조회 - 파라미터 없음")
    void testGetCommonCodeList_NoParams() {
        // when
        final List<CommonCodeDTO> result = commonCodeService.getCommonCodeList(null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - comCode1으로 조회")
    void testGetCommonCodeList_WithComCode1() {
        // given
        final String comCode1 = "SYS";

        // when
        final List<CommonCodeDTO> result = commonCodeService.getCommonCodeList(comCode1, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - comCode1, comCode2로 조회")
    void testGetCommonCodeList_WithComCode1AndComCode2() {
        // given
        final String comCode1 = "SYS";
        final String comCode2 = "001";

        // when
        final List<CommonCodeDTO> result = commonCodeService.getCommonCodeList(comCode1, comCode2, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - 전체 파라미터")
    void testGetCommonCodeList_WithAllParams() {
        // given
        final String comCode1 = "SYS";
        final String comCode2 = "001";
        final String comCode3 = "001";
        final Integer codeLvl = 1;

        // when
        final List<CommonCodeDTO> result = commonCodeService.getCommonCodeList(comCode1, comCode2, comCode3, codeLvl);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - codeLvl만 지정")
    void testGetCommonCodeList_WithCodeLvlOnly() {
        // given
        final Integer codeLvl = 2;

        // when
        final List<CommonCodeDTO> result = commonCodeService.getCommonCodeList(null, null, null, codeLvl);

        // then
        assertThat(result).isNotNull();
    }
}
