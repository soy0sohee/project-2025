package com.study.ExReviewWebApp.controller;

import com.study.ExReviewWebApp.service.impl.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {
    @GetMapping("/snsLoginSuccess")
    @ResponseBody
    public String snsLoginSuccess(HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        String username = "";
        if(user != null) {
            username = user.getName();
        }
        System.out.println(username);
        return "<script>alert('SNS로그인성공: " + username + "'); location.href='http://localhost:5173/';</script>";
    }

    @GetMapping("/snsLoginFailure")
    @ResponseBody
    public String snsLoginFailure() {
        return "<script>alert('SNS로그인실패'); history.back();</script>";
    }

    @GetMapping("/token")
    @ResponseBody
    public String getToken(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        String accessTokenString = accessToken.getTokenValue(); //토큰 문자열
        Instant issuedAt = accessToken.getIssuedAt(); //발급시간
        Instant expiresAt = accessToken.getExpiresAt(); //만료시간
        long remainSeconds = Duration.between(Instant.now(), expiresAt).getSeconds(); //남은시간

        System.out.println("발급시간: " + issuedAt);
        System.out.println("만료시간: " + expiresAt);
        System.out.println("남은시간: " + remainSeconds + "초");

        return accessTokenString;
    }
}
