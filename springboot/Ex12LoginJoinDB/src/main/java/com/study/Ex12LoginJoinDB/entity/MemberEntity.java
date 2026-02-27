package com.study.Ex12LoginJoinDB.entity;

import com.study.Ex12LoginJoinDB.dto.MemberSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name="member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder // builder 패턴 사용
public class MemberEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userRole;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate JoinDate;

    // Entity -> DTO
    public MemberSaveDto toSaveDto(){
        return MemberSaveDto.builder()
                .id(id)
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userRole(userRole)
                .JoinDate(JoinDate)
                .build();
    }
}