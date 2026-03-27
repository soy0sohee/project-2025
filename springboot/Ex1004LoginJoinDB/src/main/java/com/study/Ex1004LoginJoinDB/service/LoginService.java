package com.study.Ex1004LoginJoinDB.service;

import com.study.Ex1004LoginJoinDB.domain.MemberEntity;
import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.dto.LoginValidDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    @Transactional
    public void confrim(LoginValidDto loginValidDto) {
        String username = loginValidDto.getMemberUsername();
        String password = loginValidDto.getMemberPassword();

        MemberEntity memberEntity = memberRepository.findByMemberUsername(username)
                .orElseThrow(()->new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        if( !memberEntity.getMemberPassword().equals(password) ){
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
}
