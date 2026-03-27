package com.study.ExReviewWebApp.repository;

import com.study.ExReviewWebApp.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostFileRepository extends JpaRepository<PostFile, Long> {
}
