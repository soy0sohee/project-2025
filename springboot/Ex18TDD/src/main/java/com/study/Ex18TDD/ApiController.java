package com.study.Ex18TDD;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// loginAction() 함수 테스트
// App 실행 -> 포스트맨/JS HTTP 요청 -> 응답 확인

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {
    private final MemberService memberService;

    @PostMapping("/loginAction")
    public ResDto loginAction(@RequestBody ReqDto reqDto) {
        MemberDto memberDto = MemberDto.builder()
                .loginId(reqDto.getLoginId())
                .loginPw(reqDto.getLoginPw())
                .build();

        int result = memberService.loginAction(memberDto);

        if (result == 1) {
            return ResDto.builder()
                    .status("ok")
                    .message("로그인 성공!")
                    .build();
        } else {
            return ResDto.builder()
                    .status("ok")
                    .message("로그인 실패!")
                    .build();
        }
    }
}
