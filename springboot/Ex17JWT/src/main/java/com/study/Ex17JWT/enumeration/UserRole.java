package com.study.Ex17JWT.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    // enum: 열거형
    //     : 숫자로 값을 정하면 기억하기 어렵기때문에, 문자열 형태로 값을 정해서 가독성을 올리는것
    // 예) UserRole.USER
    USER("ROLE_USER"),ADMIN("ROLE_ADMIN");

    private String value;
}
