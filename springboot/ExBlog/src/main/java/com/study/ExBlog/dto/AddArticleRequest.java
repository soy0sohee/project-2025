package com.study.ExBlog.dto;

import com.study.ExBlog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 블로그 글 추가 요청
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    // 생성자를 사용해 객체 생성
    // toEntity() : 빌더 패턴을 사용해 DTO를 Entity로 만들어주는 메서드(DTO -> Entity)
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
