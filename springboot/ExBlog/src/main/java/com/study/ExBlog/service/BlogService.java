package com.study.ExBlog.service;

import com.study.ExBlog.domain.Article;
import com.study.ExBlog.dto.AddArticleRequest;
import com.study.ExBlog.dto.UpdateArticleRequest;
import com.study.ExBlog.repository.BlogRepository;
import com.sun.nio.sctp.IllegalUnbindException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가
    @Transactional
    public Article save(AddArticleRequest requestDto) {
        return blogRepository.save(requestDto.toEntity());
    }
    
    // 블로그 글 전체 조회
    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 하나 조회
    @Transactional(readOnly = true)
    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(()->new IllegalUnbindException(" >> not found: " + id));
    }

    // 블로그 글 삭제
    @Transactional
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    // 블로그 글 수정
    @Transactional
    public Article update(Long id, UpdateArticleRequest requestDto) {
        Article article = blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException(" >> not found: " + id));
        article.toUpdate(requestDto.getTitle(), requestDto.getContent());
        return article;
    }
}
