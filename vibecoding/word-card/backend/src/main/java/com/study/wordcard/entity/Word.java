package com.study.wordcard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "words")
@Getter
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 단어
    @Column(nullable = false)
    private String word;

    // 뜻 / 내용
    @Column(nullable = false, columnDefinition = "TEXT")
    private String meaning;

    // 생성 일시 (자동으로 현재 시간 저장)
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }
}
