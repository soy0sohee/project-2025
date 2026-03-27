package com.study.Ex17JWT.entity;

import com.study.Ex17JWT.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users_jwt")
@Getter
// @Setter: @Service + @Transactional을 사용할 때, Entity 객체를 생성해서 값을 set하면 자동으로 DB에 쓰이는데 이를 방지하기 위해 생략
@NoArgsConstructor
// UserDetails: 스프링 시큐리티에서 인증에 관련된 사용자 정보를 지원하는 인터페이스
//   -> 이메일 주소, 전화번호 등 보안과 관련 없는 사용자 정보를 편리한 위치에 저장
//   -> 인터페이스: 가상함수(추상화 메서드)만 있는 설계도
//   -> 추상화 메서드: 메서드 선언만 있고, 동작이 없는 메서드, 구현은 자식(구현)클래스에서 함
public class Users implements UserDetails {
    // 접근제한자: private 기본, 자바의 철학 중 하나인 캡슐화를 실현하기 위해 사용
    //   -> 가급적 필요한 데이터만 오픈
    //   -> C/C++에서 접근제한없이 코드를 작성하면, 유지/관리가 어려움
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true, updatable=false)
    private Long id;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="password", nullable=false)
    private String password;
    // enum타입 사용하는 이유
    // 1. 명시적으로 가독성이 좋음
    // 2. 컴파일러가 문법체크 함
    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private UserRole userRole;

    // 일부 필드만 빌드 설정
    @Builder
    public Users(String email, String password, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }

    // UserDetails 인터페이스 구현 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //            -> 인터페이스를 상속하는 어떤 구현객체 선언

        // 자바 값저장 방법: 변수/상수, 배열, List/Set/Map컬렉션

        // 컬렉션 종류
        // List: 순차적 데이터 처리를 위한 데이터 구조, 인덱스 있음
        //   -> 배열과 다른점: 삽입/수정/삭제 용이
        // Map: 순서 없이 Key-Value 데이터 구조(JSON,XML에서 사용)
        //   -> 스프링에서는 클래스 객체로 바인딩, 변수이름은 키(중복 안됨)/변수값은 밸류
        // Set: 순서 없이 중복 값을 허용하지 않는 데이터 구조, 인덱스 없음
        //   -> DB Unique 속성과 비슷
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRole.getValue()));

        // 계정의 권한 목록 리턴
        return roles;
    }

    @Override
    public String getUsername() {
        // 계정의 고유한 아이디 리턴
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정의 만료 여부
        // true: 사용가능
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정의 잠김 여부
        // true: 사용가능
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호 만료 여부
        // true: 사용가능
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정의 활성화 여부
        // true: 활성화
        return true;
    }
}
