package com.study.Ex1004LoginJoinDB.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinIdCheckValidDto {
    @NotBlank(message="아이디를 입력해주세요.")
    @Size(min = 4, max = 12, message = "아이디는 4~12자내로 입력해주세요.")
    private String memberUsername;
}
