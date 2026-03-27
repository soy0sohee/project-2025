package com.study.wordcard.controller;

import com.study.wordcard.dto.WordRequestDto;
import com.study.wordcard.dto.WordResponseDto;
import com.study.wordcard.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/words")
// 프론트엔드(localhost:3000)에서 오는 요청 허용 (CORS 설정)
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    // 단어 목록 조회
    // GET /api/words?search=apple&sort=latest
    @GetMapping
    public ResponseEntity<List<WordResponseDto>> getWords(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "latest") String sort) {

        List<WordResponseDto> words = wordService.getWords(search, sort);
        return ResponseEntity.ok(words);
    }

    // 단어 추가
    // POST /api/words
    @PostMapping
    public ResponseEntity<?> addWord(@RequestBody WordRequestDto requestDto) {
        try {
            WordResponseDto response = wordService.addWord(requestDto);
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            // 10개 초과 시 에러 메시지 반환
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // 단어 삭제
    // DELETE /api/words/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return ResponseEntity.noContent().build();
    }

    // 전체 단어 개수 조회
    // GET /api/words/count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getWordCount() {
        long count = wordService.getWordCount();
        return ResponseEntity.ok(Map.of("count", count));
    }
}
