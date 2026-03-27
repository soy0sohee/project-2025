package com.study.ExReviewWebApp.dto;

import com.study.ExReviewWebApp.entity.Post;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String rating;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private List<PostFileResponseDto> postFile;
    private List<CommentResponseDto> comment;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.rating = post.getRating();
        this.dateTime = post.getDateTime();
        this.postFile = post.getPostFile().stream()
                .map(PostFileResponseDto::new)
                .collect(Collectors.toList());
        this.comment = post.getComment().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
