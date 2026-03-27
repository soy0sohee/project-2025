package com.study.wordcard.dto;

import com.study.wordcard.entity.Word;
import lombok.Getter;

import java.time.LocalDateTime;

// 서버에서 클라이언트(프론트엔드)로 데이터를 보낼 때 사용하는 DTO
@Getter
public class WordResponseDto {

    private Long id;
    private String word;
    private String meaning;
    private LocalDateTime createdAt;

    // Word 엔티티를 받아서 DTO로 변환
    public WordResponseDto(Word word) {
        this.id = word.getId();
        this.word = word.getWord();
        this.meaning = word.getMeaning();
        this.createdAt = word.getCreatedAt();
    }
}
