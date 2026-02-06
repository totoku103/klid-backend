package com.klid.test;

import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Controller 통합 테스트 기본 클래스.
 * MockMvc를 사용하며, 실제 Oracle DB에 연결합니다.
 * TestSecurityConfig로 보안 필터가 비활성화되어 인증 없이 테스트 가능합니다.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Import(TestSecurityConfig.class)
public abstract class BaseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @BeforeEach
    void setUpSession() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        TestSessionHelper.setupMockSession();
    }

    @AfterEach
    void tearDownSession() {
        TestSessionHelper.clearMockSession();
    }
}
