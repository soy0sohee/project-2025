package com.study.Ex17JWT.service;

import com.study.Ex17JWT.dto.UserDto;
import com.study.Ex17JWT.dto.UserRequestDto;

import java.util.List;

//        | 추상화 클래스                     | 인터페이스
// 공통점 | 가상함수(추상화 메서드)를 사용함
//        | 하나의 설계(틀)로 여러개의 구현(기능) 가능
//        | -> 기존 코드를 건들이지 않고, 구현 클래스를 하나 더 만들어서 기능 확장

// 서비스 인터페이스
// 비즈니스 로직 규격 정의 -> 무엇을 할지 정의
// 데이터 가공, 검증, 처리 -> 중복체크 후 저장
public interface UsersService {  // 인터페이스로 규격(구현리스트)을 만듦
    // 회원가입
    UserDto createUser(UserRequestDto requestDto);
    // 아이디로 회원정보 조회
    UserDto findUser(String email) throws Exception;
    // 아이디/비밀번호로 회원정보 조회
    UserDto findByEmailAndPassword(String email, String password) throws Exception;
    // 전체 회원정보 조회
    List<UserDto> findAll();
}
