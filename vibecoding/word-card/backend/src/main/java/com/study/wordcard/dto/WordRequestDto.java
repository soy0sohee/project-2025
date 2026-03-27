package com.study.wordcard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 클라이언트(프론트엔드)에서 서버로 데이터를 보낼 때 사용하는 DTO
@Getter
@NoArgsConstructor
public class WordRequestDto {
    private String word;    // 단어
    private String meaning; // 뜻 / 내용
}
