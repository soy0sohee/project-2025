package com.study.Ex17JWT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  // 스프링 환경설정
public class PasswordEncoderConfig {
    @Bean  // 스프링에서 관리하는 자바 클래스 객체
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
