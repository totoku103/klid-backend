package com.klid.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Controller 통합 테스트를 위한 기본 클래스.
 * MockMvc를 사용하여 HTTP 요청/응답을 테스트합니다.
 * 실제 데이터베이스와 연동되며, 트랜잭션 후 롤백됩니다.
 */
public abstract class BaseControllerTest extends BaseIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    /**
     * 객체를 JSON 문자열로 변환합니다.
     */
    protected String toJson(final Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * JSON 문자열을 객체로 변환합니다.
     */
    protected <T> T fromJson(final String json, final Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }
}
