package com.klid.api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * 통합 테스트를 위한 기본 클래스.
 * 모든 테스트는 트랜잭션 내에서 실행되며, 테스트 완료 후 자동으로 롤백됩니다.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class BaseIntegrationTest {
    // 공통 테스트 유틸리티 메소드를 여기에 추가할 수 있습니다.
}
