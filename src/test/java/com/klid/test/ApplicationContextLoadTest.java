package com.klid.test;

import com.klid.config.TestSecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Phase 0 검증: Spring Application Context가 정상적으로 로드되는지 확인합니다.
 * DataSource, MyBatis, Security 등 모든 Bean이 올바르게 구성되는지 검증합니다.
 */
@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class ApplicationContextLoadTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Spring Application Context가 정상적으로 로드된다")
    void contextLoads() {
        assertNotNull(applicationContext, "ApplicationContext가 null이면 안 됩니다");
    }

    @Test
    @DisplayName("DataSource Bean이 존재한다")
    void dataSourceBeanExists() {
        assertNotNull(applicationContext.getBean(javax.sql.DataSource.class),
                "DataSource Bean이 존재해야 합니다");
    }

    @Test
    @DisplayName("SqlSessionFactory Bean이 존재한다")
    void sqlSessionFactoryBeanExists() {
        assertNotNull(applicationContext.getBean(org.apache.ibatis.session.SqlSessionFactory.class),
                "SqlSessionFactory Bean이 존재해야 합니다");
    }
}
