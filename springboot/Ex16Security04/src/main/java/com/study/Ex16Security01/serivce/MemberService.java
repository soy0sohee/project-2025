package com.study.Ex16Security01.serivce;

import com.study.Ex16Security01.dto.MemberJoinDto;
import com.study.Ex16Security01.dto.MemberUpdateDto;
import com.study.Ex16Security01.entity.MemberEntity;
import com.study.Ex16Security01.entity.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    // BindingResult : @Valid 유효성 검사 결과를 담는 객체
    public boolean joinAction(@Valid MemberJoinDto dto, BindingResult bindingResult) {
        // 유효성 체크 실패 시 false 반환
        if (bindingResult.hasErrors()) {
            // 모든 오류 메시지를 꺼내서 출력
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(" >> 유효성 오류: " + error.getDefaultMessage());
                // 출력 예시)
                // >> 유효성 오류: 아이디는 4자 이상이어야 합니다.
            });
            return false;
        }
        // password 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        System.out.println(" >> 비밀번호: " + dto.getPassword());
        System.out.println(" >> 암호화: " + encodedPassword);
        dto.setPassword(encodedPassword);
        // DB 저장
        try {
            memberRepository.save(dto.toSaveEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public MemberUpdateDto getDto(Long id) throws Exception {
        Optional<MemberEntity> optional = memberRepository.findById(id);
        if (optional.isEmpty()) {
            throw new Exception("member id가 잘못되었습니다.");
            // 예외처리하는 방법
            // 1. try-catch문 : 예외처리 직접 해결
            // 2. throw문 : 나를 호출한 주체가 예외처리 해결
        }
        MemberEntity entity = optional.get();
        return entity.toUpdateDto();
    }

    public boolean modifyAction(MemberUpdateDto dto) {
        // Argument : 인자, 함수 호출 할 때 넣는 변수나 값
        // Parameter : 매개변수, 함수 선언부에 입력되는 변수
        try {
            memberRepository.save(dto.toUpdateEntity());
        } catch(Exception e) {
            // printStackTrace() : 예외가 발생했을 때 오류의 상세 정보를 출력하는 메서드
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteDto(Long id) {
        try {
            memberRepository.deleteById(id);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}