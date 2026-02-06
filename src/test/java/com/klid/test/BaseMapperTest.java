package com.klid.test;

import com.klid.config.TestSecurityConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * MyBatis Mapper 통합 테스트 기본 클래스.
 * 실제 Oracle DB에 연결하여 테스트하며, @Transactional로 자동 롤백됩니다.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestSecurityConfig.class)
public abstract class BaseMapperTest {
}
