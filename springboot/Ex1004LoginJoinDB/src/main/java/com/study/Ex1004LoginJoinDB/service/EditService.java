package com.study.Ex1004LoginJoinDB.service;

import com.study.Ex1004LoginJoinDB.domain.MemberEntity;
import com.study.Ex1004LoginJoinDB.domain.MemberRepository;
import com.study.Ex1004LoginJoinDB.dto.MemberResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditService {
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long memberNo, MemberResponseDto memberResponseDto) {
        MemberEntity memberEntity = memberRepository.findById(memberNo).orElseThrow();

        String username = memberResponseDto.getMemberUsername();
        String email = memberResponseDto.getMemberEmail();
        String password = memberResponseDto.getMemberPassword();
        String role = memberResponseDto.getMemberRole();

        memberEntity.updateMember(username, email, password, role);
        memberRepository.save(memberEntity);
    }

    @Transactional
    public void delete(Long memberNo) {
        MemberEntity memberEntity = memberRepository.findById(memberNo).orElseThrow();
        memberRepository.delete(memberEntity);
    }
}
