package com.study.Ex17JWT.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    //1. 기본함수: findAll(), findById(), save(), deleteById(), count()

    //2. 사용자정의함수: 열이름, DB부사절(WHERE, OR, AND, GROUP BY, ORDER BY, DESC, ASC, LIMIT) -> 이름이 너무 길어지는 단점이 있음

    // SQL Query: SELECT * FROM users_jwt WHERE email = :email;
    // Optional: Null 관리가 편함
    Optional<Users> findByEmail(String email);

    // SQL Query: SELECT * FROM users_jwt WHERE email = :email AND password = :password;
    Optional<Users> findByEmailAndPassword(String email, String password);

    //3. @Query: Native Query(AnsiSQL), JPQL(Entity)
}
