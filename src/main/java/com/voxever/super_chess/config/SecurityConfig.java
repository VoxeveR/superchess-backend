package com.voxever.super_chess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Updated syntax
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws/**").permitAll()  // WebSocket endpoint
                        .requestMatchers("/topic/**").permitAll()  // STOMP topics
                        .requestMatchers("/app/**").permitAll()    // STOMP app destinations
                        .requestMatchers("/user/**").permitAll()    // STOMP app destinations
                        .requestMatchers("/game/**").permitAll()    // STOMP app destinations
                        .anyRequest().permitAll()
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin()  // Allow frames for WebSocket
                );

        return http.build();
    }
}