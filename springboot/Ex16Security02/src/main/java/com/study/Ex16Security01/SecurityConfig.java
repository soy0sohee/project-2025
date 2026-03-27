package com.study.Ex16Security01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

// 시큐리티 관련 설정 클래스
@Configuration  // 환경설정 클래스로 등록
@EnableWebSecurity  // 웹 보안 활성화 어노테이션
public class SecurityConfig {
    @Bean  // 메서드(함수) 반환객체를 빈으로 등록
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {  // 시큐리티가 발생하면 그쪽에서 처리해라

        http
                // CSRF 보안 설정을 비활성화(개발편의시)
                // .csrf((auth)->auth.disable())

                // CSRF 보안 설정을 활성화(기본) 보안 방식
                // 1. HttpSession(기본) : 서버에 인증정보 저장
                // 2. CookieToken : 자바스크립트 기반 앱 제작시 쿠키에 CsrfToken 저장
                // 람다매개변수 타입 : CsrfConfigurer<HttpSecurity>
                .csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                // 경로별 접근 권한 설정(Security 6버전) - 인가
                // 람다매개변수 타입 : 	AuthorizationManagerRequestMatcherRegistry
                .authorizeHttpRequests((authorization) -> authorization
                        // .requestMatchers("/") : root("/") URL을
                        //      .permitAll() : 모두에게 허용
                        //      .authenticated() : 인증된 사용자에게만 허용
                        //      .hasRole("ADMIN") : ADMIN 권한을 가진 사용자에게만 허용. DB에는 "ROLE_ADMIN" 문구로 기록됨
                        .requestMatchers("/loginForm").permitAll()
                        .requestMatchers("/joinForm").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")

                        // 그외의 경로 요청에 대해서 인증된 사용에게만 허용. 예) /mypage
                        .anyRequest().authenticated()
                )

                // 로그인 페이지/액션 설정
                // 람다매개변수 타입 : FormLoginConfigurer<HttpSecurity>
                .formLogin((formLogin) -> formLogin
                        // .loginPage("/loginForm") : 로그인 폼 요청 URL
                        // .loginProcessingUrl("/loginAction") : 로그인 액션 요청 URL. loginAction에 대한 인증 처리는 시큐리티가 함. 코드 필요없음
                        // .defaultSuccessUrl("/") : 로그인 성공시 리다이렉트 URL
                        // .successHandler(()->{}) : 로그인 성공 커스텀 핸들러
                        // .failureUrl("/loginForm?error") : 로그인 실패 리다이렉트 URL
                        // .permitAll() : 모두에게 허용
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/loginAction")
                        .defaultSuccessUrl("/")
                        .successHandler((request, response, auth) -> {
                            System.out.println(" >> 로그인 성공했습니다.");
                            response.sendRedirect("/");
                        })
                        .failureUrl("/loginForm?error")
                        .permitAll()
                )

                // 로그아웃 URL/세션 설정
                // 람다매개변수 타입 : LogoutConfigurer<HttpSecurity>
                .logout((logout) -> logout
                        // .loginRequestMatcher() : 스프링부트 4.x 업데이트 된 클래스 함수
                        .logoutRequestMatcher(
                                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET, "/logoutAction")
                        )
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)  // 세션 객체 해제
                        .deleteCookies("JSESSIONID")  // 쿠키 삭제
                );
        
        return http.build();
    }

    @Bean  // password 암호화 설정
    public PasswordEncoder passwordEncoder() {

        // 시큐리티 기본 암호화 객체
        // BCrypt : 암호화 엔코더

        // 스프링 시큐리티  6.4.x에서 공식 지원하는 passwordEncoder 구현 클래스
        // 1. BcryptPasswordEncoder
        // 2. Argon2PasswordEncoder
        // 3. Pbkdf2PasswordEncoder
        // 4. SCryptPasswordEncoder

        // 암호화 강도 4~31까지 지정 가능, 기본 10
         return new BCryptPasswordEncoder(10);

        // return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        // return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        // return SCrypt2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        
        // return new BcryptPassword4jPasswordEncoder(); -> Password4j 외부라이브러리로 시큐리티 7에 추가 됨, 공식이 아니므로 비추천
    }
}
