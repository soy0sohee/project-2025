package com.study.ExReviewWebApp.repository;

import com.study.ExReviewWebApp.entity.SnsUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SnsUsersRepository extends JpaRepository<SnsUsers, Long> {
    Optional<SnsUsers> findByEmail(String email);
}
