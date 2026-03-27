package com.study.Ex1004LoginJoinDB.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_no", nullable = false)
    private Long memberNo;
    @Column(name="member_username", nullable = false)
    private String memberUsername;
    @Column(name="member_password", nullable = false)
    private String memberPassword;
    @Column(name="member_email")
    private String memberEmail;
    @Column(name="member_joindate")
    private LocalDate memberJoindate;
    @Column(name="member_role")
    private String memberRole = "user";

    public void updateMember(String memberUsername, String memberEmail, String memberPassword, String memberRole) {
        this.memberUsername = memberUsername;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
    }
}
