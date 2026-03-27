package com.study.Ex16Security01;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements UserDetailsService {

    @Override  // 사용자 아이디를 통해 사용자 정보와 권한(ROLE)을 스프링 시큐리티에 전달해주는 코드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorityList = new ArrayList<>();  // 리스트에 추가
        authorityList.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));  // 테스트로 관리자 권한 한개 추가

        System.out.println(" >> 시큐리티가 사용자 정보를 조회함");

        // 권한과 함께 사용자 정보 객체 반환 (username : "admin" / password : "1234")
        // password는 반드시 암호화해서 넘거야 함
        // Bcrypt사이트(bcrypt-generator.com)에서 암호를 생성해서 붙여넣음
        return new User("admin","$2a$10$HhBKgpvxDDlfVf/K8j0QT.3qho5.6/2XC86CgLGNtJpU/SrwUcAzK", authorityList);
    }

}
