package com.klid.api.system.code;

import com.klid.api.BaseServiceTest;
import com.klid.api.system.code.dto.CodeRequest;
import com.klid.api.system.code.dto.CodeResponse;
import com.klid.api.system.code.dto.WeekDayRequest;
import com.klid.api.system.code.service.CodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CodeService 통합 테스트
 */
class CodeServiceTest extends BaseServiceTest {

    @Autowired
    private CodeService codeService;

    @Test
    @DisplayName("코드 목록 조회 - 빈 파라미터")
    void getCodeList_WithEmptyParams_ReturnsEmptyOrList() {
        // given
        final Map<String, Object> params = new HashMap<>();

        // when
        final List<CodeResponse> result = codeService.getCodeList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("코드 목록 조회 - 코드 타입 필터")
    void getCodeList_WithCodeType_ReturnsFilteredList() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("codeType", "COMMON");

        // when
        final List<CodeResponse> result = codeService.getCodeList(params);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("코드 등록")
    void addCode_Success() {
        // given
        final CodeRequest request = new CodeRequest();
        request.setCodeType("TEST_TYPE");
        request.setCodeId("TEST_CODE_001");
        request.setCodeName("테스트 코드");
        request.setCodeDesc("테스트 설명");
        request.setUseYn("Y");
        request.setSortOrder(1);

        // when & then - 예외가 발생하지 않으면 성공
        codeService.addCode(request);
    }

    @Test
    @DisplayName("코드 수정")
    void editCode_Success() {
        // given
        final CodeRequest request = new CodeRequest();
        request.setCodeType("TEST_TYPE");
        request.setCodeName("수정된 코드명");
        request.setCodeDesc("수정된 설명");
        request.setUseYn("N");

        // when & then - 예외가 발생하지 않으면 성공
        codeService.editCode("EXISTING_CODE", request);
    }

    @Test
    @DisplayName("코드 중복 체크 - 존재하지 않는 코드")
    void getCodeDuplCnt_NotExists_ReturnsZeroOrNull() {
        // given
        final Map<String, Object> params = new HashMap<>();
        params.put("codeType", "NON_EXISTENT_TYPE");
        params.put("codeId", "NON_EXISTENT_CODE");

        // when
        final Integer result = codeService.getCodeDuplCnt(params);

        // then
        assertThat(result == null || result == 0).isTrue();
    }

    @Test
    @DisplayName("공휴일 등록")
    void addWeekDay_Success() {
        // given
        final WeekDayRequest request = new WeekDayRequest();
        request.setWeekdayDate("20251225");
        request.setWeekdayName("크리스마스");
        request.setWeekdayType("HOLIDAY");
        request.setUseYn("Y");

        // when & then - 예외가 발생하지 않으면 성공
        codeService.addWeekDay(request);
    }

    @Test
    @DisplayName("공휴일 삭제")
    void delWeekDay_Success() {
        // given
        final String weekdayId = "NON_EXISTENT_WEEKDAY";

        // when & then - 예외가 발생하지 않으면 성공
        codeService.delWeekDay(weekdayId);
    }
}
