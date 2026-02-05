package com.klid.webapp.realtimeNotify.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

import static org.mockito.Mockito.*;

class RealtimeNotifyWebSocketConfigTest {

    private RealtimeNotifyWebSocketConfig config;

    @BeforeEach
    void setUp() {
        config = new RealtimeNotifyWebSocketConfig();
    }

    @Nested
    @DisplayName("configureMessageBroker()")
    class ConfigureMessageBroker {

        @Test
        @DisplayName("SimpleBroker가 /topic과 /queue로 설정된다")
        void configuresSimpleBroker() {
            MessageBrokerRegistry registry = mock(MessageBrokerRegistry.class);
            when(registry.enableSimpleBroker(any(String[].class))).thenReturn(null);
            when(registry.setApplicationDestinationPrefixes(any(String[].class))).thenReturn(registry);
            when(registry.setUserDestinationPrefix(anyString())).thenReturn(registry);

            config.configureMessageBroker(registry);

            verify(registry).enableSimpleBroker("/topic", "/queue");
        }

        @Test
        @DisplayName("Application destination prefix가 /app으로 설정된다")
        void configuresApplicationDestinationPrefix() {
            MessageBrokerRegistry registry = mock(MessageBrokerRegistry.class);
            when(registry.enableSimpleBroker(any(String[].class))).thenReturn(null);
            when(registry.setApplicationDestinationPrefixes(any(String[].class))).thenReturn(registry);
            when(registry.setUserDestinationPrefix(anyString())).thenReturn(registry);

            config.configureMessageBroker(registry);

            verify(registry).setApplicationDestinationPrefixes("/app");
        }

        @Test
        @DisplayName("User destination prefix가 /user로 설정된다")
        void configuresUserDestinationPrefix() {
            MessageBrokerRegistry registry = mock(MessageBrokerRegistry.class);
            when(registry.enableSimpleBroker(any(String[].class))).thenReturn(null);
            when(registry.setApplicationDestinationPrefixes(any(String[].class))).thenReturn(registry);
            when(registry.setUserDestinationPrefix(anyString())).thenReturn(registry);

            config.configureMessageBroker(registry);

            verify(registry).setUserDestinationPrefix("/user");
        }
    }

    @Nested
    @DisplayName("registerStompEndpoints()")
    class RegisterStompEndpoints {

        @Test
        @DisplayName("엔드포인트가 /ws/realtime-notify로 등록된다")
        void registersEndpoint() {
            StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
            StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);

            when(registry.addEndpoint("/ws/realtime-notify")).thenReturn(registration);
            when(registration.setAllowedOriginPatterns("*")).thenReturn(registration);
            when(registration.withSockJS()).thenReturn(null);

            config.registerStompEndpoints(registry);

            verify(registry).addEndpoint("/ws/realtime-notify");
        }

        @Test
        @DisplayName("모든 origin이 허용된다")
        void allowsAllOrigins() {
            StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
            StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);

            when(registry.addEndpoint("/ws/realtime-notify")).thenReturn(registration);
            when(registration.setAllowedOriginPatterns("*")).thenReturn(registration);
            when(registration.withSockJS()).thenReturn(null);

            config.registerStompEndpoints(registry);

            verify(registration).setAllowedOriginPatterns("*");
        }

        @Test
        @DisplayName("SockJS가 활성화된다")
        void enablesSockJS() {
            StompEndpointRegistry registry = mock(StompEndpointRegistry.class);
            StompWebSocketEndpointRegistration registration = mock(StompWebSocketEndpointRegistration.class);

            when(registry.addEndpoint("/ws/realtime-notify")).thenReturn(registration);
            when(registration.setAllowedOriginPatterns("*")).thenReturn(registration);
            when(registration.withSockJS()).thenReturn(null);

            config.registerStompEndpoints(registry);

            verify(registration).withSockJS();
        }
    }
}
