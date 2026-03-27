package com.study.Ex1004LoginJoinDB.controller;

import com.study.Ex1004LoginJoinDB.domain.MemberEntity;
import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.dto.MemberResponseDto;
import com.study.Ex1004LoginJoinDB.service.EditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/edit")
public class EditController {
    private final MemberRepository memberRepository;
    private final EditService editService;

    @GetMapping("/data")
    @ResponseBody
    public MemberResponseDto data(@RequestParam Long memberNo){
        MemberEntity memberentity = memberRepository.findById(memberNo).orElseThrow();

        String username = memberentity.getMemberUsername();
        String email = memberentity.getMemberEmail();
        String password = memberentity.getMemberPassword();
        String role = memberentity.getMemberRole();

        return new MemberResponseDto(username, email, password, role);
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(@RequestParam Long memberNo, @RequestBody MemberResponseDto memberResponseDto){
        editService.save(memberNo, memberResponseDto);
        return "ok";
    }
}
