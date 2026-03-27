package com.study.Ex1004LoginJoinDB.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginValidDto {
    @NotBlank(message="아이디를 다시 입력해주세요.")
    @Size(min=4, max=12, message = "아이디를 다시 입력해주세요.")
    private String memberUsername;
    @NotBlank(message="비밀번호를 다시 입력해주세요.")
    @Size(min=4, max=12, message = "비밀번호를 다시 입력해주세요.")
    private String memberPassword;
}
