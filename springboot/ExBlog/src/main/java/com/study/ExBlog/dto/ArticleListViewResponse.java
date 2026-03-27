package com.study.ExBlog.dto;

import com.study.ExBlog.domain.Article;
import lombok.Getter;

// 블로그 글 리스트 응답
@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
