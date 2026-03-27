package com.study.ExReviewWebApp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

//하나의 HTTP 요청당 딱 한번 실행되도록 보장해주는 필터
//  -> GenericFilterBean: 기본 필터
//  -> OncePerRequestFilter: 중복 실행 방지 기능이 추가된 필터
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    private final JwtUtil jwtUtil;

    //요청객체, 응답객체, 토캣으로 받아옴
    //  -> IOException: IO 문제 예외 처리
    //  -> ServletException: 내부 예외 처리
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //생성된 토큰 호출
        String token = jwtUtil.resolveToken((HttpServletRequest) request);

        //토큰이 null이 아니고, 검증 확인 되면 -> 유저정보를 조회해서 SecurityContextHolder에 저장
        if (token != null && jwtUtil.validateToken(token)) {
            Authentication authentication = jwtUtil.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //다음 필터로 요청,응답 값을 보냄
        filterChain.doFilter(request, response);
    }
}
