package com.study.Ex1004LoginJoinDB.controller;

import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.dto.JoinIdCheckValidDto;
import com.study.Ex1004LoginJoinDB.dto.JoinValidDto;
import com.study.Ex1004LoginJoinDB.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {
    private final JoinService joinService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    @ResponseBody
    public String signup(@Valid @RequestBody JoinValidDto joinValidDto) {
        joinService.signup(joinValidDto);
        return "ok";
    }

    @PostMapping("/duplicate")
    @ResponseBody
    public String duplicate(@Valid @RequestBody JoinIdCheckValidDto joinIdCheckValidDto){
        String username = joinIdCheckValidDto.getMemberUsername();
        boolean exists = memberRepository.findByMemberUsername(username).isPresent();
        System.out.println(username);
        System.out.println(exists);
        if(!exists){
            return "ok";
        } else {
            throw new IllegalArgumentException("중복 아이디가 있습니다.");
        }
    }
}
