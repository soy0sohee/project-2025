package com.study.Ex17JWT.config;

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

// 톰캣 WAS에 보안 필터에 끼워지는 추가적인 필터
@RequiredArgsConstructor
// JwtAuthenticationFilter: 사용자가 직접 커스텀한 필터
public class JwtAuthenticationFilter extends GenericFilterBean {
    // 토큰 발행
    private final JwtUtil jwtTokenProvider;

    @Override
    //                   -> 요청객체             -> 응답객체               -> 톰캣으로 받음
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // HTTP 요청 헤더에서 JWT를 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        System.out.println(">> 토큰: " + token);
        System.out.println(">> 유효성: " + (token != null ? jwtTokenProvider.validateToken(token) : "토큰없음"));

        // 유효한 토큰인지 확인
        if(token != null && jwtTokenProvider.validateToken(token)){
            // 토큰이 유효하면 토큰으로부터 유저 정보를 가져옴
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            System.out.println(">> 권한: " + authentication.getAuthorities());

            // SecurityContext에 Authentication객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
