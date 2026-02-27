package com.study.Ex10RealDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository : @Component가 들어간 JPA에서 DB제어 클래스
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // JpaRepository의 기본 함수 : find 관련 함수가 무수히 많음
    // 1. findAll() : SELECT * FROM TABLE -> SQL 실행
    // 2. findBy열이름(값) : SELECT 컬럼명 FROM TABLE WHERE 컬럼명 = 값 -> SQL 실행
    //                     : 예) findById((Long)2) : select*from Table where id=2
    //                     :     findByUserName("홍길동") : select*from Table where userName="홍길동"
    // 3. save(E) : INSERT와 UPDATE -> SQL 실행
    //            : id값을 확인하고 있으면 update(수정), 없으면 insert(생성)
    // 4. delete(E) : DELETE -> SQL 실행
}
