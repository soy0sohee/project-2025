package com.study.Ex16Security01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// 시큐리티 관련 설정 클래스
@Configuration  // 환경설정 클래스로 등록
@EnableWebSecurity  // 웹 보안 활성화 어노테이션
public class SecurityConfig {
    @Bean  // 메서드(함수) 반환객체를 빈으로 등록
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  // 시큐리티가 발생하면 그쪽에서 처리해라

        http    // HTTP 요청에 대한 보안 설정(Security 6버전)
                .authorizeHttpRequests((auth)->auth
                        // .requestMatchers("/") : root("/") URL을
                        //      .permitAll() : 모두에게 허용
                        //      .authenticated() : 인증된 사용자에게만 허용
                        //      .hasRole("ADMIN") : ADMIN 권한을 가진 사용자에게만 허용
                        .requestMatchers("/loginForm").permitAll()
                        .requestMatchers("/joinForm").permitAll()
                        .requestMatchers("/").authenticated()
                        .requestMatchers("/admin").hasRole("ADMIN")  // DB에는 "ROLE_ADMIN" 문구로 기록됨

                        // 그외의 경로 요청에 대해서 인증된 사용에게만 허용함. 예) /mypage
                        .anyRequest().authenticated()
                )
                // 로그인 페이지 설정
                .formLogin((formLogin)-> formLogin
                        // .loginPage("/loginForm") : 로그인 폼 요청 URL
                        // .loginProcessingUrl("/loginAction") : 로그인 액션 요청 URL
                        // .defaultSuccessUrl("/") : 로그인 성공시 리다이텍트 URL
                        // .permitAll() : 모두에게 허용
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/loginAction")  // loginAction에 대한 인증 처리는 시큐리티가 함. 코드 필요없음
                        .defaultSuccessUrl("/")
                        .permitAll()
                );
        
        return http.build();
    }
}
