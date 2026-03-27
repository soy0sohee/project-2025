package com.study.ExBlog.dto;

import com.study.ExBlog.domain.Article;
import lombok.Getter;

// 블로그 글 하나 응답
@Getter
public class ArticleResponse {
    private String title;
    private String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
