package com.study.Ex16Security01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "spring boot security web/app 입니다.";
    }

    // 인증되지 않은 사용자여도, 로그인, 회원가입 페이지로 접근 가능해야 함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm"; // 시큐리티 적용후에는 권한이 없어서 안됨
    }
}