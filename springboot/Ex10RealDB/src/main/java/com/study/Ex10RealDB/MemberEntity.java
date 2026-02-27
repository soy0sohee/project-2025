package com.study.Ex10RealDB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

// @Entity : 엔티티 클래스임, DB테이블과 1:1 매핑되는 클래스
@Entity
@Table(name="member")
@Getter
// @Setter : 넣지 않음, 개발자의 실수나 자동으로 호출되는 경우를 제거
@AllArgsConstructor
@NoArgsConstructor // 기본생성자 필수, @ModelAttribute @RequestBody에 필요
public class MemberEntity {
    // @Id : 기본키 id열로 사용한다는 의미
    @Id
    // @GeneratedValue : id값을 어떻게 생성할지 전략을 선택
    // 1. IDENTITY : MySQL, MariaDB, PostgreSQL, H2DB
    // 2. SEQUENCE : Oracle, PostgreSQL
    // 3. AUTO : 자동으로 선택
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userId; // DB = Snake Case 선호, JAVA = Camel Case 선호
    private String userPw;
    private String userName;
    private String userRole;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate JoinDate;
}

// MySQL : BIGINT / INT     / VARCHAR(n) / TEXT   / DATE      / DATETIME      / BLOB   / TinyInt(1)
// JAVA  : Long   / Integer / String     / String / LocalDate / LocalDateTime / byte[] / Boolean
//                                                  Date -> Format(pattern) 없으면 오류