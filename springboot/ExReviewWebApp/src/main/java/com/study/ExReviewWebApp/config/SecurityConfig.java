package com.study.ExReviewWebApp.config;

import com.study.ExReviewWebApp.service.impl.OAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final OAuth2UserServiceImpl oAuth2UserServiceImpl;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        //기본 환경 설정
        http
                //CORS 다른 기원 요청 제어
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()));
        http
                //CSRF 보안 비활성화(개발 편의용)
                .csrf((csrf) -> csrf.disable());

        http
                //경로별 접근 권한 모든 요청 인가 허용(개발 편의용)
                .authorizeHttpRequests((authorization) -> authorization
                        .anyRequest().permitAll()
                );

        //JWT-Login 환경 설정
        http
                //인증 완료 후 세션을 안전하게 관리
                .sessionManagement((sessionConfig) -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http
                //JwtFilter를 로그인 필터 앞에 사용
                .addFilterBefore(
                        new JwtFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class
                );

        //SNS-Login 환경 설정
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userinfo) -> userinfo.userService(oAuth2UserServiceImpl))
                        .successHandler(new SimpleUrlAuthenticationSuccessHandler("/snsLoginSuccess"))
                        .failureHandler(new SimpleUrlAuthenticationFailureHandler("/snsLoginFailure"))
                );

        return http.build();
    }

    @Bean //password 암호화 설정
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10); //암호화 강도: 10
    }

    @Bean //CORS 허용 범위 설정
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        //쿠키 인증 요청
        corsConfig.setAllowCredentials(true);

        //HTTP header, method, 출처
        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
        corsConfig.setAllowedMethods(Collections.singletonList("*"));
        corsConfig.setAllowedOriginPatterns(Collections.singletonList("*"));

        //모든 URL 경로에 위에서 작성한 CORS 설정을 매핑
        UrlBasedCorsConfigurationSource urlBaseSource = new UrlBasedCorsConfigurationSource();
        urlBaseSource.registerCorsConfiguration("/**", corsConfig);

        return urlBaseSource;
    }

}
