package com.study.Ex16Security01.entity;

import com.study.Ex16Security01.dto.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="member_security")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // -> 전체 필드만 가진 생성자
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(name="nick_name")
    private String nickName;
    @Column(name="user_role")
    private String userRole;
    @Column(name="join_date")
    private LocalDate joinDate;

    public MemberUpdateDto toUpdateDto() {
        return MemberUpdateDto.builder()
                .id(id)
                .username(username)
                .password(password)
                .nickName(nickName)
                .userRole(userRole)
                .joinDate(joinDate)
                .build();
    }

    // 엔티티를 HTML 폼 매핑에 직접 사용하지 않고 DTO를 사용하는 것을 추천함
    // 엔티티 객체를 잘못 사용하면, 테이블에 직접 Write될 여지가 있음
    // @Transational 서비스 클래스에서 setter함수 사용시 바로 DB에 적용됨

    // @Builder -> 일부 필드만 가진 생성자
    // public MemberEntity() {
    // }
}
