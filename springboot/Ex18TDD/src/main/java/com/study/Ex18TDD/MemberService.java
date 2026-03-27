package com.study.Ex18TDD;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public int loginAction(MemberDto memberDto) {
        if(memberDto.getLoginId().equals("hong") && memberDto.getLoginPw().equals("1234")) {
            return 1; // 로그인 성공
        } else {
            return 2; // 로그인 실패
        }
    }
}
