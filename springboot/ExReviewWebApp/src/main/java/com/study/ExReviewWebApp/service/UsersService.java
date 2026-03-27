package com.study.ExReviewWebApp.service;

//service를 인터페이스 + 구현클래스로 나누는 이유
//유연성: 나중에 구편체만 교체 가능
//테스트 용이: 가짜 객체를 만들어 단위 테스트 편함
//의존선 역전: 인터페이스에 의존, 유지보수가 쉬움

import com.study.ExReviewWebApp.dto.UserDto;
import com.study.ExReviewWebApp.dto.UserRequestDto;

import java.util.List;

public interface UsersService {
    //회원가입
    UserDto createUser(UserRequestDto requestDto);

    //아이디로 회원정보 조회
    UserDto findUser(String email);

    //아이디+비번으로 회원정보 조회
    UserDto findUserAndPassword(String email, String password);

    //전체 회원정보 조회
    List<UserDto> findAll();
}
