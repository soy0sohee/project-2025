package com.study.Ex16Security01;

import lombok.Getter;

// 시큐리티 종류
//                    인증(Authentication)      인가(Authorization)
// 1. 의미            신원 확인                 권한 부여
// 2. 우선순위        가장 먼저                 인증 완료 후 수행
// 3. 실패시 응답     401 Unauthorized          403 Forbidden
// 4. 주요 데이터     ID/PW,JWT토큰,session     Role(역할),Scope(범위)

// enum : 열거형 - 상수나 변수를 열거하여, 가독성있게 만드는 역할
// 예) 0 = user -> UserRole.USER
//     1 = admin -> UserRole.ADMIN
@Getter
public enum UserRole {
    // HTTP요청 시큐리티에서 .requestMatchers("/admin").hasRole("ADMIN")로 권환을 주면 DB에는 "ROLE_ADMIN" 문구로 기록됨
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

    // enum 생성자는 public 안씀, 각 상수 선언시 자동 호출
    // UserRole roe = UserRole.User; -> 상수 선언
    UserRole(String value) {
        this.value = value;
    }
}
