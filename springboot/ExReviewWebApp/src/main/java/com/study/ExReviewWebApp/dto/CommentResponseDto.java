package com.study.ExReviewWebApp.dto;

import com.study.ExReviewWebApp.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime dateTime;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.dateTime = comment.getDateTime();
    }
}
