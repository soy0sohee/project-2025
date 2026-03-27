package com.study.ExReviewWebApp.service;

import com.study.ExReviewWebApp.dto.CommentRequestDto;
import com.study.ExReviewWebApp.entity.Comment;
import com.study.ExReviewWebApp.entity.Post;
import com.study.ExReviewWebApp.repository.CommentRepository;
import com.study.ExReviewWebApp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void commentSave(long id, CommentRequestDto commentRequestDto) {
        Optional<Post> optional = postRepository.findById(id);
        if(optional.isEmpty()){
            throw new IllegalArgumentException("회원 없음");
        }
        Post post = optional.get();
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .dateTime(LocalDateTime.now())
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    @Transactional
    public void commentRemove(long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if(optional.isEmpty()){
            throw new IllegalArgumentException("회원 없음");
        }
        commentRepository.deleteById(id);
    }
}
