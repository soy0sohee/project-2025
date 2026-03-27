package com.study.ExReviewWebApp.controller;

import com.study.ExReviewWebApp.config.JwtUtil;
import com.study.ExReviewWebApp.dto.UserDto;
import com.study.ExReviewWebApp.dto.UserRequestDto;
import com.study.ExReviewWebApp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class JwtController {
    private final UsersService usersService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String createUser(@RequestBody UserRequestDto requestDto) {
        usersService.createUser(requestDto);
        return "ok";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto requestDto) {
        System.out.println("계정: "+requestDto.getEmail());

        UserDto userDto = null;

        try {
            userDto = usersService.findUserAndPassword(requestDto.getEmail(), requestDto.getPassword());
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("로그인 오류: " + e.getMessage());
            return "exception";
        }

        return jwtUtil.createToken(userDto.getEmail(), Arrays.asList(userDto.getUserRole().getValue()));
    }
}
