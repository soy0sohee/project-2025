package com.study.Ex1004LoginJoinDB.controller;

import com.study.Ex1004LoginJoinDB.domain.MemberEntity;
import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.service.EditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberRepository memberRepository;
    private final EditService editService;

    @PostMapping("")
    @ResponseBody
    public List<MemberEntity> member() {
        return memberRepository.findAll();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long memberNo) {
        editService.delete(memberNo);
        return "ok";
    }
}
