package com.FoodApp.FoodiesApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Simple Broker active karein messages dynamic routings ke liye
        config.enableSimpleBroker("/topic");

        // App destination prefix (/app) set karein jahan payload bhejenge
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // SockJS ke fallback support ke sath browser connection bypass karna
        registry.addEndpoint("/ws-tracking")
                .setAllowedOriginPatterns("*") // Sabhi frontend origins ko strictly allow karne ke liye
                .withSockJS(); // SockJS fallback support add kiya
    }
}