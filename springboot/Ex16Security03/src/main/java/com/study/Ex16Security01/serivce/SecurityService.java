package com.study.Ex16Security01.serivce;

import com.study.Ex16Security01.entity.MemberEntity;
import com.study.Ex16Security01.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    // 사용자 아이디를 통해 사용자 정보와 권한(ROLE)을 스프링 시큐리티에 전달해주는 코드
    // 시큐리티에서 로그인 액션에서 사용자 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB에서 실사용자 정보를 가져와서 시큐리티에 넘겨줌
        Optional<MemberEntity> optional = memberRepository.findByUsernameJPQL(username);
        if(optional.isEmpty()) {
            throw new UsernameNotFoundException(" >> 사용자를 찾을 수 없습니다.");
        }
        MemberEntity entity = optional.get();

        List<GrantedAuthority> authorityList = new ArrayList<>();  // 리스트에 추가
        authorityList.add(new SimpleGrantedAuthority(entity.getUserRole()));  // 테스트로 관리자 권한 한개 추가
        System.out.println(" >> 시큐리티가 사용자 정보를 조회함");

        // 권한과 함께 사용자 정보 객체 반환 (username : "admin" / password : "1234")
        // password는 반드시 암호화해서 넘거야 함
        // Bcrypt사이트(bcrypt-generator.com)에서 암호를 생성해서 붙여넣음
        return new User(entity.getUsername(), entity.getPassword(), authorityList);
    }
}
