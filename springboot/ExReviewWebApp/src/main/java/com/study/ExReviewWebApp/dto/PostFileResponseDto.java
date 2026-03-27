package com.study.ExReviewWebApp.dto;

import com.study.ExReviewWebApp.entity.PostFile;
import lombok.Getter;

@Getter
public class PostFileResponseDto {
    private Long id;
    private String fileName;

    public PostFileResponseDto(PostFile postFile) {
        this.id = postFile.getId();
        this.fileName = postFile.getFileName();
    }
}
