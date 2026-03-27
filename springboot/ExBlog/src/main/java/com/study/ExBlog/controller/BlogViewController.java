package com.study.ExBlog.controller;

import com.study.ExBlog.domain.Article;
import com.study.ExBlog.dto.ArticleListViewResponse;
import com.study.ExBlog.dto.ArticleViewResponse;
import com.study.ExBlog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {
    private final BlogService blogService;

    // 블로그 글 전체 조회
    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> list = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", list);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticles(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", article);
        return "article";
    }

    // @RequestParam(required=false) = 파라미터가 없어도 오류가 발생하지 않도록 설정하는 옵션
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required=false) Long id, Model model) {
        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}
