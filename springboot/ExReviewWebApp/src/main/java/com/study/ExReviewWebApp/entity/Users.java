package com.study.ExReviewWebApp.entity;

import com.study.ExReviewWebApp.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name="users")
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private UserRole userRole;

    public String getRoleKey() {
        return this.userRole.getValue();
    }

    @Override //계정 권한 목록
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> role = new HashSet<>();
        role.add(new SimpleGrantedAuthority(this.userRole.getValue()));
        return role;
    }

    @Override //계정 고유 아이디
    public String getUsername() {
        return this.email;
    }

    @Override //계정 만료 여부
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //계정 잠김 여부
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //비밀번호 만료 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정 활성화 여부
    public boolean isEnabled() {
        return true;
    }
}
