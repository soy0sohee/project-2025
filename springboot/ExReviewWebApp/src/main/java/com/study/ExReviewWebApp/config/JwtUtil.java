package com.study.ExReviewWebApp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

//토큰 생성 및 검증 유틸
@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secretKey}") //토큰키
    private String secretKey;
    @Value("${jwt.expiration_time}") //유효기간
    private long expireTime;

    private final UserDetailsService userDetailsService;

    @PostConstruct //토큰 초기화
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //토큰 발급(로그인 성공 시 호출)
    //토큰 구조
    //  -> Header: 알고리즘 정보(HS256)
    //  -> Payload: 이메일, 권한, 발행시간, 만료시간
    //  -> Signature: secretKey로 서명
    public String createToken(String email, List<String> userRole) {
        //토큰 안에 들어있는 정보(payload)
        Claims claims = Jwts.claims()
                .setSubject(email)
                .add("roles", userRole)
                .build();
        Date nowDate = new Date();
        return Jwts.builder()
                .setClaims(claims) //이메일, 권한
                .setIssuedAt(nowDate) //발행시간
                .setExpiration(new Date(nowDate.getTime() + expireTime)) //만료시간
                .signWith(SignatureAlgorithm.HS256, secretKey) //Signature에 들어가는 secretKey
                .compact();
    }

    //토큰 추출
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("JWT_TOKEN");
    }

    //토큰 검증
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token); //변조확인 + 만료확인
            return claimsJws.getBody()
                    .getExpiration()
                    .before(new Date()) == false; //토큰 시간이 현재 시간 이전인가? == false; -> return true;
        } catch (Exception e) {
            return false;
        }
    }

    //유저정보 조회
    public Authentication getAuthentication(String token) {
        String email = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
