package com.study.ExReviewWebApp.repository;

import com.study.ExReviewWebApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
