package com.study.Ex17JWT.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity // 웹 접근권한
@EnableMethodSecurity(securedEnabled = true) // 함수 접근권한, @Secured에서 메서드 호출 전 보안 활성화
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // CORS 설정을 해줌
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // REST API 서버에서는 주로 비활성화
                // -> REST API는 인증을 토큰으로 받으므로 csrf 공격을 받지 않음
                .csrf((csrf) -> csrf.disable());

        httpSecurity
                .authorizeHttpRequests((authorization) -> authorization
                        // ("/**") 하위 경로 모두 포함
                        // 모든 루트 경로의 인가 해제
                        .requestMatchers("/**").permitAll()
                );

        // 스프링 시큐리티에서 세션관리(html 기반) 정책을 관리안함으로 설정
        // JWT 토큰 기반 서버에서 세션관리는 토큰으로 하기 때문에
        httpSecurity
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // JWT 보안 필터를 특정 필터 앞에 추가
        httpSecurity
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class
                );

        return httpSecurity.build();
    }

    // CORS 설정
    // (색인):1 Access to fetch at 'http://localhost:8080/api/users/signup'
    // from origin 'http://localhost:5173' has been blocked by CORS policy:
    // No 'Access-Control-Allow-Origin' header is present on the requested resource.
    // -> CROS 오류 문구: 8080 서버에서 응답할때, 'Access-Control-Allow-Origin' 응답 헤더에 5173 포트를 허용해준다는 정보가 없음
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedHeaders(Collections.singletonList("*")); // 허용할 HTTP header
        config.setAllowedMethods(Collections.singletonList("*")); // 허용할 HTTP method
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // 허용할 출처
        // config.setAllowedOriginPatterns(Collections.singletonList("http://127.0.0.1:8080")); // 허용할 출처
        config.setAllowCredentials(true); // 쿠키 인증 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
