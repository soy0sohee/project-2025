package com.study.Ex16Security01.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 기본함수 지원
    // 유저 정의 함수는 별도 기입
    // @Query를 이용하여 SQL을 직접 사용하기 : JPQL, Native Query(Select문)

    // JPQL 정의함수 선언 -> 객체지향성을 추구한다면 JPQL 선호
    @Query(value = "SELECT member FROM MemberEntity AS member WHERE member.username = :username_param")
    Optional<MemberEntity> findByUsernameJPQL(@Param("username_param") String username);

    // Native Query 정의함수 선언
    // @Query(value = "SELECT * FROM member_security AS member WHERE member.username = :username_param", nativeQuery = true)
    // Optional<MemberEntity> findByUsernameNative(@Param("username_param") String username);
}
