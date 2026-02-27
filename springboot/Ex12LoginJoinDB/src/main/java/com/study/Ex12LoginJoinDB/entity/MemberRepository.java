package com.study.Ex12LoginJoinDB.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 사용자 함수 정의
    // private String userId; -> findByUserId
    // 쿼리 자동 생성 : SELECT * FROM 테이블 WHERE userId = "userId 변수값"
    Optional<MemberEntity> findByUserId(String userId);
    
    // JPA 커스텀 쿼리 메서드 생성 규칙
    // https://velog.io/@633jinn/JPARepository-%EB%A9%94%EC%86%8C%EB%93%9C-%EC%BB%A4%EC%8A%A4%ED%85%80%ED%95%98%EA%B8%B0
    // 단점 - 모든 검색을 함수 호출로 불가능
    
    // JPA에서 네이티브SQL,JPQL(JPA SQL) 사용하는 방법
}
