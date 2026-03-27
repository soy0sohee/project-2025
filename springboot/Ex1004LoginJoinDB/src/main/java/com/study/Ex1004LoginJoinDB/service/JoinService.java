package com.study.Ex1004LoginJoinDB.service;

import com.study.Ex1004LoginJoinDB.domain.MemberEntity;
import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.dto.JoinValidDto;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository memberRepository;

    @Transactional
    @Builder
    public void signup(@RequestBody JoinValidDto joinValidDto) {
        String username = joinValidDto.getMemberUsername();
        String email = joinValidDto.getMemberEmail();
        String password = joinValidDto.getMemberPassword();

        MemberEntity memberEntity = MemberEntity.builder()
                .memberUsername(username)
                .memberEmail(email)
                .memberPassword(password)
                .memberJoindate(LocalDate.now())
                .memberRole("user")
                .build();
        memberRepository.save(memberEntity);
    }
}
