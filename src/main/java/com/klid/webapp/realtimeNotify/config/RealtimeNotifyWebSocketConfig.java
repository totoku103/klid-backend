package com.klid.webapp.realtimeNotify.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * RealtimeNotify WebSocket Configuration
 * STOMP 메시지 브로커 설정 - 사건 접수 알림 브로드캐스트용
 */
@Configuration
@EnableWebSocketMessageBroker
public class RealtimeNotifyWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 구독할 수 있는 prefix 설정
        registry.enableSimpleBroker("/topic", "/queue");
        // 클라이언트가 메시지를 보낼 때 사용할 prefix
        registry.setApplicationDestinationPrefixes("/app");
        // 특정 사용자에게 메시지를 보낼 때 사용할 prefix
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 엔드포인트 설정 with SockJS fallback
        registry.addEndpoint("/ws/realtime-notify")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
