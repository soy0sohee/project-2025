package com.study.Ex1004LoginJoinDB.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
    private String memberUsername;

    public LoginResponseDto(String memberUsername) {
        this.memberUsername = memberUsername;
    }
}
