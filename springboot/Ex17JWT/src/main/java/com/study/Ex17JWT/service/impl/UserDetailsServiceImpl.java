package com.study.Ex17JWT.service.impl;

import com.study.Ex17JWT.entity.Users;
import com.study.Ex17JWT.entity.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
// 로그인 시 DB에서 사용자를 찾아서 Spring Security에게 건네주는 역할
// UserDetailsService: 유저를 찾는 방법을 정의하는 인터페이스
//                   : DB에서 유저 찾는 방법을 알려주는 설계도
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository userRepository;

    @Override
    public Users loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        // 1. DB에서 이메일로 유저 찾기
        Optional<Users> optional = userRepository.findByEmail(userEmail);
        // 2. 없으면 예외
        if( optional.isEmpty() ){
            throw  new UsernameNotFoundException("이메일에 맞는 회원이 없습니다.");
        }
        // 3. Users 엔티티 반환
        Users entity = optional.get();

        return entity;
    }
}

// @Service
// @RequiredArgsConstructor
// public class UserDetailsServiceImpl implements UserDetailsService {
//     private final UsersRepository usersRepo;
//
//     @Override
//     // UserDerails: 유저정보+권한을 담는 객체
//     // loadUserByUsername: DB에서 유저 찾는 메서드
//     public UserDetails loadUserByUsername(String email) {
//         return usersRepo.findByEmail(email)
//                 .map(user -> User.builder()
//                         .username(user.getEmail())
//                         .password(user.getPassword())
//                         .authorities(user.getUserRole().getValue())
//                         .build())
//                 .orElseThrow(() -> new UsernameNotFoundException("user not found"));
//     }
// }
