package com.study.ExReviewWebApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDto {
    private String uuid;
    private String fileName;
    private String contentType;
    private String filePath;
}
