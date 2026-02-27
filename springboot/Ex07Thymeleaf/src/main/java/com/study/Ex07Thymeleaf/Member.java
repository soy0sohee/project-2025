package com.study.Ex07Thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String username; // 스프링 시큐리티에서 사용자 계정 의미
    private String password; // 스프링 시큐리티에서 사용자 비번 의미
}
