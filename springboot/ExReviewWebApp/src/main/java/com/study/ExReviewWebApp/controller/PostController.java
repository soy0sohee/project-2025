package com.study.ExReviewWebApp.controller;

import com.study.ExReviewWebApp.dto.PostRequestDto;
import com.study.ExReviewWebApp.dto.PostResponseDto;
import com.study.ExReviewWebApp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public List<PostResponseDto> list() {
        return postService.list();
    }

    @PostMapping("/create")
    //JSON+파일을 같이 받으려면 @RequestPart을 써야함
    public ResponseEntity<?> create(@RequestPart("data") PostRequestDto data, @RequestPart("file") MultipartFile[] file)  throws IOException {
        System.out.println("컨트롤러 진입");
        System.out.println("파일 개수: " + file.length);
        postService.create(data, file);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/postDetail/{id}")
    public PostResponseDto list(@PathVariable long id) {
        return postService.postDetail(id);
    }
}
