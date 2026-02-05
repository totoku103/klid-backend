package com.klid.api.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * 통합 테스트 기본 클래스
 * - 실제 데이터베이스(10.1.2.99)를 사용
 * - 트랜잭션 롤백으로 데이터 정합성 유지
 */
@SpringBootTest
@ActiveProfiles("integration-test")
@Transactional
public abstract class IntegrationTestBase {
    // 모든 통합 테스트의 기본 설정
    // @Transactional 어노테이션으로 테스트 후 자동 롤백
}
