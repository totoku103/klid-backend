package com.klid.api.common.code.persistence;

import com.klid.api.BaseMapperTest;
import com.klid.api.common.code.dto.CommonCodeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 공통 코드 Mapper 통합 테스트
 */
@DisplayName("공통 코드 Mapper 테스트")
class CommonCodeMapperTest extends BaseMapperTest {

    @Autowired
    private CommonCodeMapper commonCodeMapper;

    @Test
    @DisplayName("공통 코드 목록 조회 - 파라미터 없음")
    void testSelectCommonCode_NoParams() {
        // when
        final List<CommonCodeDTO> result = commonCodeMapper.selectCommonCode(null, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - comCode1으로 조회")
    void testSelectCommonCode_WithComCode1() {
        // given
        final String comCode1 = "SYS";

        // when
        final List<CommonCodeDTO> result = commonCodeMapper.selectCommonCode(comCode1, null, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - comCode1, comCode2로 조회")
    void testSelectCommonCode_WithComCode1AndComCode2() {
        // given
        final String comCode1 = "SYS";
        final String comCode2 = "001";

        // when
        final List<CommonCodeDTO> result = commonCodeMapper.selectCommonCode(comCode1, comCode2, null, null);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - 전체 파라미터")
    void testSelectCommonCode_WithAllParams() {
        // given
        final String comCode1 = "SYS";
        final String comCode2 = "001";
        final String comCode3 = "001";
        final Integer codeLvl = 1;

        // when
        final List<CommonCodeDTO> result = commonCodeMapper.selectCommonCode(comCode1, comCode2, comCode3, codeLvl);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("공통 코드 목록 조회 - codeLvl만 지정")
    void testSelectCommonCode_WithCodeLvlOnly() {
        // given
        final Integer codeLvl = 2;

        // when
        final List<CommonCodeDTO> result = commonCodeMapper.selectCommonCode(null, null, null, codeLvl);

        // then
        assertThat(result).isNotNull();
    }
}
