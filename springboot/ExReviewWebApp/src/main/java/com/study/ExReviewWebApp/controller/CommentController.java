package com.study.ExReviewWebApp.controller;

import com.study.ExReviewWebApp.dto.CommentRequestDto;
import com.study.ExReviewWebApp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/commentSave/{id}")
    public ResponseEntity<?> commentSave(@PathVariable long id, @RequestBody CommentRequestDto commentRequestDto) {
        System.out.println(commentRequestDto.getContent());
        commentService.commentSave(id, commentRequestDto);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/commentRemove/{id}")
    public ResponseEntity<?> commentRemove(@PathVariable long id) {
        System.out.println(id);
        commentService.commentRemove(id);
        return ResponseEntity.ok("ok");
    }
}
