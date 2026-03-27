package com.study.Ex1004LoginJoinDB.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinValidDto {
    @NotBlank(message="아이디를 입력해주세요.")
    @Size(min = 4, max = 12, message = "아이디는 4~12자로 입력해주세요.")
    private String memberUsername;
    @Email (message = "이메일 형식이 아닙니다.")
    private String memberEmail;
    @NotBlank(message="비밀번호를 입력해주세요.")
    @Size(min = 4, max = 12, message = "비밀번호는 4~12자로 입력해주세요.")
    private String memberPassword;
}
