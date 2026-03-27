package com.study.Ex1004LoginJoinDB.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberNo;
    private String memberUsername;
    private String memberPassword;
    private String memberEmail;
    private String memberRole;

    public MemberResponseDto(String memberUsername, String memberEmail, String memberPassword, String memberRole){
        this.memberUsername = memberUsername;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
    }
}
