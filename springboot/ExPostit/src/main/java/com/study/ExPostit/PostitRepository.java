package com.study.ExPostit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostitRepository extends JpaRepository<Postit, Long> {
    // 기본함수 지원 (CRUD)
}
