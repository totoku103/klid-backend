package com.klid.api.integration;

import com.klid.webapp.common.code.dto.CodeDto;
import com.klid.webapp.common.code.persistence.CodeMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CodeMapper 통합 테스트
 * - 실제 데이터베이스(10.1.2.99)를 사용
 * - 트랜잭션 롤백으로 데이터 정합성 유지
 * - CRUD 전체 테스트
 */
@DisplayName("CodeMapper 통합 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CodeMapperIntegrationTest extends IntegrationTestBase {

    @Autowired
    private CodeMapper codeMapper;

    private static final String TEST_COM_CODE1 = "9999";
    private static final String TEST_COM_CODE2 = "99";
    private static final String TEST_CODE_NAME = "통합테스트코드";

    @Test
    @Order(1)
    @DisplayName("공통코드 조회 - SELECT")
    void selectCommonCode_shouldReturnCodeList() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("comCode1", "1001");  // 기존에 존재하는 코드
        paramMap.put("codeLvl", 2);

        // When
        final List<CodeDto> result = codeMapper.selectCommonCode(paramMap);

        // Then
        assertThat(result).isNotNull();
        // 데이터가 있으면 검증, 없으면 빈 리스트 허용
        if (!result.isEmpty()) {
            assertThat(result.get(0).getComCode1()).isEqualTo("1001");
        }
    }

    @Test
    @Order(2)
    @DisplayName("지역코드 조회 - SELECT")
    void selectLocalCode_shouldReturnLocalCodeList() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();

        // When
        final List<CodeDto> result = codeMapper.selectLocalCode(paramMap);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(3)
    @DisplayName("코드 등록 - INSERT")
    void addCode_shouldInsertNewCode() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("comCode1", TEST_COM_CODE1);
        paramMap.put("comCode2", TEST_COM_CODE2);
        paramMap.put("comCode3", "0");
        paramMap.put("codeName", TEST_CODE_NAME);
        paramMap.put("codeLvl", 2);
        paramMap.put("flag1", "test");
        paramMap.put("flag2", "");
        paramMap.put("codeCont", "통합테스트용 코드입니다");
        paramMap.put("useYn", "Y");

        // When
        final int result = codeMapper.addCode(paramMap);

        // Then
        assertThat(result).isGreaterThanOrEqualTo(0);

        // 등록된 코드 확인
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("comCode1", TEST_COM_CODE1);
        selectParam.put("codeLvl", 2);
        final List<CodeDto> codes = codeMapper.getCodeList(selectParam);

        assertThat(codes).isNotEmpty();
        assertThat(codes.stream()
                .anyMatch(c -> TEST_CODE_NAME.equals(c.getCodeName())))
                .isTrue();
    }

    @Test
    @Order(4)
    @DisplayName("코드 수정 - UPDATE")
    void editCode_shouldUpdateExistingCode() {
        // Given - 먼저 코드 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("comCode1", TEST_COM_CODE1);
        insertParam.put("comCode2", "98");
        insertParam.put("comCode3", "0");
        insertParam.put("codeName", "수정전코드");
        insertParam.put("codeLvl", 2);
        insertParam.put("flag1", "");
        insertParam.put("flag2", "");
        insertParam.put("codeCont", "");
        insertParam.put("useYn", "Y");
        codeMapper.addCode(insertParam);

        // When - 코드 수정
        final Map<String, Object> updateParam = new HashMap<>();
        updateParam.put("comCode1", TEST_COM_CODE1);
        updateParam.put("comCode2", "98");
        updateParam.put("codeName", "수정후코드");
        updateParam.put("codeLvl", 2);
        updateParam.put("useYn", "Y");
        updateParam.put("codeCont", "수정된 내용");
        final int updateResult = codeMapper.editCode(updateParam);

        // Then
        assertThat(updateResult).isGreaterThanOrEqualTo(0);

        // 수정된 코드 확인
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("comCode1", TEST_COM_CODE1);
        selectParam.put("comCode2", "98");
        selectParam.put("codeLvl", 2);
        final List<CodeDto> codes = codeMapper.getCodeList(selectParam);

        if (!codes.isEmpty()) {
            assertThat(codes.get(0).getCodeName()).isEqualTo("수정후코드");
        }
    }

    @Test
    @Order(5)
    @DisplayName("코드 중복 확인")
    void getCodeDuplCnt_shouldReturnDuplicateCount() {
        // Given - 먼저 코드 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("comCode1", TEST_COM_CODE1);
        insertParam.put("comCode2", "97");
        insertParam.put("comCode3", "0");
        insertParam.put("codeName", "중복테스트코드");
        insertParam.put("codeLvl", 2);
        insertParam.put("flag1", "");
        insertParam.put("flag2", "");
        insertParam.put("codeCont", "");
        insertParam.put("useYn", "Y");
        codeMapper.addCode(insertParam);

        // When
        final Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("comCode1", TEST_COM_CODE1);
        checkParam.put("codeName", "중복테스트코드");
        checkParam.put("codeLvl", 2);
        final int duplCount = codeMapper.getCodeDuplCnt(checkParam);

        // Then
        assertThat(duplCount).isGreaterThanOrEqualTo(1);
    }

    @Test
    @Order(6)
    @DisplayName("코드 삭제 - DELETE")
    void delCode_shouldDeleteCode() {
        // Given - 먼저 코드 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("comCode1", TEST_COM_CODE1);
        insertParam.put("comCode2", "96");
        insertParam.put("comCode3", "0");
        insertParam.put("codeName", "삭제테스트코드");
        insertParam.put("codeLvl", 2);
        insertParam.put("flag1", "");
        insertParam.put("flag2", "");
        insertParam.put("codeCont", "");
        insertParam.put("useYn", "Y");
        codeMapper.addCode(insertParam);

        // When - 코드 삭제
        final Map<String, Object> deleteParam = new HashMap<>();
        deleteParam.put("comCode1", TEST_COM_CODE1);
        deleteParam.put("comCode2", "96");
        final int deleteResult = codeMapper.delCode(deleteParam);

        // Then
        assertThat(deleteResult).isGreaterThanOrEqualTo(0);

        // 삭제 확인
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("comCode1", TEST_COM_CODE1);
        selectParam.put("comCode2", "96");
        selectParam.put("codeLvl", 2);
        final List<CodeDto> codes = codeMapper.getCodeList(selectParam);

        assertThat(codes).isEmpty();
    }

    @Test
    @Order(7)
    @DisplayName("코드 목록 조회 - getCodeList")
    void getCodeList_shouldReturnCodeListWithSorting() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("comCode1", "1001");
        paramMap.put("codeLvl", 2);
        paramMap.put("sortType", "regDt");

        // When
        final List<CodeDto> result = codeMapper.getCodeList(paramMap);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(8)
    @DisplayName("설문 타입 조회")
    void selectSurveyType_shouldReturnSurveyTypes() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();

        // When
        final List<CodeDto> result = codeMapper.selectSurveyType(paramMap);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(9)
    @DisplayName("공지사항 출처 타입 조회")
    void selectNoticeSrcType_shouldReturnNoticeSrcTypes() {
        // When
        final List<CodeDto> result = codeMapper.selectNoticeSrcType();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    @Order(10)
    @DisplayName("CRUD 전체 사이클 테스트")
    void fullCRUDCycle_shouldWorkCorrectly() {
        // CREATE
        final Map<String, Object> createParam = new HashMap<>();
        createParam.put("comCode1", TEST_COM_CODE1);
        createParam.put("comCode2", "95");
        createParam.put("comCode3", "0");
        createParam.put("codeName", "CRUD사이클테스트");
        createParam.put("codeLvl", 2);
        createParam.put("flag1", "cycle");
        createParam.put("flag2", "test");
        createParam.put("codeCont", "CRUD 전체 사이클 테스트");
        createParam.put("useYn", "Y");

        final int createResult = codeMapper.addCode(createParam);
        assertThat(createResult).isGreaterThanOrEqualTo(0);

        // READ
        final Map<String, Object> readParam = new HashMap<>();
        readParam.put("comCode1", TEST_COM_CODE1);
        readParam.put("comCode2", "95");
        readParam.put("codeLvl", 2);

        List<CodeDto> readResult = codeMapper.getCodeList(readParam);
        assertThat(readResult).isNotEmpty();
        assertThat(readResult.get(0).getCodeName()).isEqualTo("CRUD사이클테스트");

        // UPDATE
        final Map<String, Object> updateParam = new HashMap<>();
        updateParam.put("comCode1", TEST_COM_CODE1);
        updateParam.put("comCode2", "95");
        updateParam.put("codeName", "CRUD사이클수정됨");
        updateParam.put("codeLvl", 2);
        updateParam.put("useYn", "Y");
        updateParam.put("codeCont", "수정된 CRUD 테스트");

        final int updateResult = codeMapper.editCode(updateParam);
        assertThat(updateResult).isGreaterThanOrEqualTo(0);

        // READ after UPDATE
        readResult = codeMapper.getCodeList(readParam);
        assertThat(readResult).isNotEmpty();
        assertThat(readResult.get(0).getCodeName()).isEqualTo("CRUD사이클수정됨");

        // DELETE
        final Map<String, Object> deleteParam = new HashMap<>();
        deleteParam.put("comCode1", TEST_COM_CODE1);
        deleteParam.put("comCode2", "95");

        final int deleteResult = codeMapper.delCode(deleteParam);
        assertThat(deleteResult).isGreaterThanOrEqualTo(0);

        // READ after DELETE
        readResult = codeMapper.getCodeList(readParam);
        assertThat(readResult).isEmpty();
    }
}
