package com.study.Ex16Security01.entity;

import com.study.Ex16Security01.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sns_user")
@Getter
@NoArgsConstructor
public class SnsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="picture", nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role", nullable = false)
    private UserRole role;

    @Builder
    public SnsUser(String name, String email, String picture, UserRole role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public SnsUser update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getValue();
    }
}
