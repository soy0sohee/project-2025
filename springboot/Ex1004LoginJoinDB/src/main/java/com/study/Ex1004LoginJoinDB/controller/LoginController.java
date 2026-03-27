package com.study.Ex1004LoginJoinDB.controller;

import com.study.Ex1004LoginJoinDB.domain.MemberEntity;
import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.dto.LoginValidDto;
import com.study.Ex1004LoginJoinDB.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final MemberRepository memberRepository;

    // @Valid Dto 검증 실행
    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid @RequestBody LoginValidDto loginValidDto) {
        loginService.confrim(loginValidDto);

        Optional<MemberEntity> optional = memberRepository.findByMemberUsername(loginValidDto.getMemberUsername());

        String role = optional.map((memberEntity) ->
            memberEntity.getMemberRole().equals("admin") ? "admin" : "user"
        ).orElseThrow();

        return role;
    }
}
