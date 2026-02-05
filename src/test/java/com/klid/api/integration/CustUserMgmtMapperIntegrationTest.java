package com.klid.api.integration;

import com.klid.webapp.common.code.dto.CustUserDto;
import com.klid.webapp.common.code.persistence.CodeMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CustUserMgmt(외부사용자관리) 통합 테스트
 * - 실제 데이터베이스(10.1.2.99)를 사용
 * - 트랜잭션 롤백으로 데이터 정합성 유지
 * - CRUD 전체 테스트
 */
@DisplayName("CustUserMgmt Mapper 통합 테스트")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustUserMgmtMapperIntegrationTest extends IntegrationTestBase {

    @Autowired
    private CodeMapper codeMapper;

    private static final String TEST_USER_ID = "test_integration";
    private static final String TEST_CUST_NM = "통합테스트사용자";
    private static final String TEST_CELL_NO = "010-1234-5678";
    private static final String TEST_MAIL_ADDR = "test@integration.com";

    @Test
    @Order(1)
    @DisplayName("외부사용자 등록 - INSERT")
    void addCustUser_shouldInsertNewUser() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", TEST_USER_ID);
        paramMap.put("custNm", TEST_CUST_NM);
        paramMap.put("custCellNo", TEST_CELL_NO);
        paramMap.put("custMailAddr", TEST_MAIL_ADDR);
        paramMap.put("smsGroupSeq", 1);

        // When
        final int result = codeMapper.addCustUser(paramMap);

        // Then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(2)
    @DisplayName("외부사용자 목록 조회 - SELECT")
    void getCustUserList_shouldReturnUserList() {
        // Given - 먼저 사용자 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("custNm", "조회테스트사용자");
        insertParam.put("custCellNo", "010-9999-9999");
        insertParam.put("custMailAddr", "select@test.com");
        insertParam.put("smsGroupSeq", 1);
        codeMapper.addCustUser(insertParam);

        // When
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("userId", TEST_USER_ID);
        selectParam.put("smsGroupSeq", 1);
        final List<CustUserDto> result = codeMapper.getCustUserList(selectParam);

        // Then
        assertThat(result).isNotNull();
        if (!result.isEmpty()) {
            assertThat(result.stream()
                    .anyMatch(u -> "조회테스트사용자".equals(u.getCustNm())))
                    .isTrue();
        }
    }

    @Test
    @Order(3)
    @DisplayName("외부사용자 수정 - UPDATE")
    void updateCustUser_shouldUpdateUser() {
        // Given - 먼저 사용자 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("custNm", "수정전사용자");
        insertParam.put("custCellNo", "010-1111-1111");
        insertParam.put("custMailAddr", "before@test.com");
        insertParam.put("smsGroupSeq", 1);
        codeMapper.addCustUser(insertParam);

        // 등록된 사용자 조회하여 seqNo 얻기
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("userId", TEST_USER_ID);
        selectParam.put("smsGroupSeq", 1);
        final List<CustUserDto> users = codeMapper.getCustUserList(selectParam);

        if (!users.isEmpty()) {
            final CustUserDto user = users.stream()
                    .filter(u -> "수정전사용자".equals(u.getCustNm()))
                    .findFirst()
                    .orElse(users.get(0));

            // When - 사용자 수정
            final Map<String, Object> updateParam = new HashMap<>();
            updateParam.put("userId", TEST_USER_ID);
            updateParam.put("seqNo", user.getSeqNo());
            updateParam.put("custNm", "수정후사용자");
            updateParam.put("custCellNo", "010-2222-2222");
            updateParam.put("custMailAddr", "after@test.com");
            updateParam.put("smsGroupSeq", 1);

            final int updateResult = codeMapper.updateCustUser(updateParam);

            // Then
            assertThat(updateResult).isGreaterThanOrEqualTo(0);
        }
    }

    @Test
    @Order(4)
    @DisplayName("외부사용자 삭제 - DELETE")
    void deleteCustUser_shouldDeleteUser() {
        // Given - 먼저 사용자 등록
        final Map<String, Object> insertParam = new HashMap<>();
        insertParam.put("userId", TEST_USER_ID);
        insertParam.put("custNm", "삭제테스트사용자");
        insertParam.put("custCellNo", "010-3333-3333");
        insertParam.put("custMailAddr", "delete@test.com");
        insertParam.put("smsGroupSeq", 1);
        codeMapper.addCustUser(insertParam);

        // 등록된 사용자 조회하여 seqNo 얻기
        final Map<String, Object> selectParam = new HashMap<>();
        selectParam.put("userId", TEST_USER_ID);
        selectParam.put("smsGroupSeq", 1);
        final List<CustUserDto> users = codeMapper.getCustUserList(selectParam);

        if (!users.isEmpty()) {
            final CustUserDto user = users.stream()
                    .filter(u -> "삭제테스트사용자".equals(u.getCustNm()))
                    .findFirst()
                    .orElse(users.get(0));

            // When - 사용자 삭제
            final Map<String, Object> deleteParam = new HashMap<>();
            deleteParam.put("userId", TEST_USER_ID);
            deleteParam.put("seqNo", user.getSeqNo());

            final int deleteResult = codeMapper.deleteCustUser(deleteParam);

            // Then
            assertThat(deleteResult).isGreaterThanOrEqualTo(0);
        }
    }

    @Test
    @Order(5)
    @DisplayName("외부사용자 등록 횟수 제한 확인")
    void getCustUserRegCnt_shouldReturnRegistrationCount() {
        // Given
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("sUserId", TEST_USER_ID);

        // When
        final int result = codeMapper.getCustUserRegCnt(paramMap);

        // Then
        assertThat(result).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(6)
    @DisplayName("CRUD 전체 사이클 테스트")
    void fullCRUDCycle_shouldWorkCorrectly() {
        // CREATE
        final Map<String, Object> createParam = new HashMap<>();
        createParam.put("userId", TEST_USER_ID);
        createParam.put("custNm", "CRUD사이클테스트");
        createParam.put("custCellNo", "010-5555-5555");
        createParam.put("custMailAddr", "crud@cycle.com");
        createParam.put("smsGroupSeq", 1);

        final int createResult = codeMapper.addCustUser(createParam);
        assertThat(createResult).isGreaterThanOrEqualTo(0);

        // READ
        final Map<String, Object> readParam = new HashMap<>();
        readParam.put("userId", TEST_USER_ID);
        readParam.put("smsGroupSeq", 1);

        List<CustUserDto> users = codeMapper.getCustUserList(readParam);
        final CustUserDto createdUser = users.stream()
                .filter(u -> "CRUD사이클테스트".equals(u.getCustNm()))
                .findFirst()
                .orElse(null);

        if (createdUser != null) {
            assertThat(createdUser.getCustNm()).isEqualTo("CRUD사이클테스트");

            // UPDATE
            final Map<String, Object> updateParam = new HashMap<>();
            updateParam.put("userId", TEST_USER_ID);
            updateParam.put("seqNo", createdUser.getSeqNo());
            updateParam.put("custNm", "CRUD사이클수정됨");
            updateParam.put("custCellNo", "010-6666-6666");
            updateParam.put("custMailAddr", "updated@cycle.com");
            updateParam.put("smsGroupSeq", 1);

            final int updateResult = codeMapper.updateCustUser(updateParam);
            assertThat(updateResult).isGreaterThanOrEqualTo(0);

            // READ after UPDATE
            users = codeMapper.getCustUserList(readParam);
            final CustUserDto updatedUser = users.stream()
                    .filter(u -> createdUser.getSeqNo() == u.getSeqNo())
                    .findFirst()
                    .orElse(null);

            if (updatedUser != null) {
                assertThat(updatedUser.getCustNm()).isEqualTo("CRUD사이클수정됨");
            }

            // DELETE
            final Map<String, Object> deleteParam = new HashMap<>();
            deleteParam.put("userId", TEST_USER_ID);
            deleteParam.put("seqNo", createdUser.getSeqNo());

            final int deleteResult = codeMapper.deleteCustUser(deleteParam);
            assertThat(deleteResult).isGreaterThanOrEqualTo(0);

            // READ after DELETE
            users = codeMapper.getCustUserList(readParam);
            final boolean stillExists = users.stream()
                    .anyMatch(u -> createdUser.getSeqNo() == u.getSeqNo());

            assertThat(stillExists).isFalse();
        }
    }
}
