package com.klid.test;

import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service 통합 테스트 기본 클래스.
 * 실제 Oracle DB에 연결하며, SessionManager 사용을 위해
 * TestSessionHelper로 MockSession을 자동 설정합니다.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestSecurityConfig.class)
public abstract class BaseServiceTest {

    @BeforeEach
    void setUpSession() {
        TestSessionHelper.setupMockSession();
    }

    @AfterEach
    void tearDownSession() {
        TestSessionHelper.clearMockSession();
    }
}
