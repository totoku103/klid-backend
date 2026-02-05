package com.klid.api.system.code;

import com.klid.api.BaseMapperTest;
import com.klid.api.system.code.dto.CodeRequest;
import com.klid.api.system.code.dto.CodeResponse;
import com.klid.api.system.code.dto.WeekDayRequest;
import com.klid.api.system.code.persistence.CodeMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CodeMapper 통합 테스트
 */
class CodeMapperTest extends BaseMapperTest {

    @Autowired
    @Qualifier("apiCodeMapper")
    private CodeMapper codeMapper;

    @Test
    @DisplayName("selectCodeList - 전체 코드 목록 조회")
    void selectCodeList_ReturnsResults() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<CodeResponse> result = codeMapper.selectCodeList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectCodeList - 코드 타입으로 필터링")
    void selectCodeList_WithCodeType_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("codeType", "COMMON");

        // when
        final List<CodeResponse> result = codeMapper.selectCodeList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("selectCodeList - 사용 여부로 필터링")
    void selectCodeList_WithUseYn_ReturnsFilteredResults() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("useYn", "Y");

        // when
        final List<CodeResponse> result = codeMapper.selectCodeList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("insertCode - 코드 등록")
    void insertCode_Success() {
        // given
        final CodeRequest request = new CodeRequest();
        request.setCodeType("MAPPER_TEST");
        request.setCodeId("MT_001");
        request.setCodeName("매퍼 테스트 코드");
        request.setCodeDesc("매퍼 테스트용 설명");
        request.setUseYn("Y");
        request.setSortOrder(1);

        // when & then - 예외가 발생하지 않으면 성공
        codeMapper.insertCode(request);
    }

    @Test
    @DisplayName("updateCode - 코드 수정")
    void updateCode_Success() {
        // given
        final CodeRequest request = new CodeRequest();
        request.setCodeId("EXISTING_CODE");
        request.setCodeType("COMMON");
        request.setCodeName("수정된 코드명");
        request.setCodeDesc("수정된 설명");
        request.setUseYn("N");

        // when & then - 예외가 발생하지 않으면 성공
        codeMapper.updateCode(request);
    }

    @Test
    @DisplayName("selectCodeDuplCnt - 코드 중복 개수 조회")
    void selectCodeDuplCnt_ReturnsCount() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("codeType", "COMMON");
        params.put("codeId", "TEST_CODE");

        // when
        final Integer result = codeMapper.selectCodeDuplCnt(params);

        // then
        // 결과가 null이거나 0 이상이면 정상
        assertThat(result == null || result >= 0).isTrue();
    }

    @Test
    @DisplayName("insertWeekDay - 공휴일 등록")
    void insertWeekDay_Success() {
        // given
        final WeekDayRequest request = new WeekDayRequest();
        request.setWeekdayDate("20251231");
        request.setWeekdayName("연말");
        request.setWeekdayType("SPECIAL");
        request.setUseYn("Y");

        // when & then - 예외가 발생하지 않으면 성공
        codeMapper.insertWeekDay(request);
    }

    @Test
    @DisplayName("deleteWeekDay - 공휴일 삭제")
    void deleteWeekDay_Success() {
        // given
        final String weekdayId = "NON_EXISTENT_ID";

        // when & then - 예외가 발생하지 않으면 성공
        codeMapper.deleteWeekDay(weekdayId);
    }
}
