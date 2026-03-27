package com.study.ExBlog.controller;

import com.study.ExBlog.domain.Article;
import com.study.ExBlog.dto.AddArticleRequest;
import com.study.ExBlog.dto.ArticleResponse;
import com.study.ExBlog.dto.UpdateArticleRequest;
import com.study.ExBlog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    // >> 블로그 글 추가
    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    // ResponseEntity<응답데이터타입> : HTTP 응답 전체를 직접 제어할 때 사용하는 클래스, 상태코드+헤더+body
    // @RequestBody로 요청 본문 값 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest requestDto) {
        System.out.println(" >> 바인딩 성공: " + requestDto);
        Article article = blogService.save(requestDto);

        // 상태 코드 설정
        // 200: 성공, 201: 생성, 400: 잘못된요청, 401: 인증실패, 403: 권한없음, 404: 데이터없음, 500: 서버오류
        // ResponseEntity.status() = 원하는 상태코드를 직접 지정할 때 사용
        // HttpStatus.CREATED = 201
        // .body() = 응답 데이터를 넣는 메서드
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(article);
    }

    // >> 블로그 전체 조회
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> list = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        // ResponseEntity.ok() = 200
        return ResponseEntity.ok().body(list);
    }

    // >> 블로그 하나 조회
    // URL 경로에서 값 추출
    // @PathVariable : URL 경로에 있는 값을 가져오는 애너테이션
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    // >> 블로그 글 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);

        // .build() = body 없이 응답만 만들 때 사용
        return ResponseEntity.ok().build();
    }

    // >> 블로그 글 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest requestDto) {
        Article article = blogService.update(id,requestDto);

        return ResponseEntity.ok().body(article);
    }
}

