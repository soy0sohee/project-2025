package com.study.Ex16Security01.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnsUserRepository extends JpaRepository<SnsUser, Long> {
    // 기본 함수 : findAll, findById, save, deleteById, count
    // 사용자 정의 함수 : 열이름 + And, Or, Where, By, Asc, Desc, limit 조합
    // @Query - Native Query, JPQL

    // * SNS 로그인으로 반환되는 값 중에서 email을 통해 이미 회원가입된 사용자인지, 처음 가입한 사용자인지 구분하는 쿼리 메서드
    Optional<SnsUser> findByEmail(String email);
}
