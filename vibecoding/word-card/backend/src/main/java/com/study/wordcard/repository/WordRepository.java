package com.study.wordcard.repository;

import com.study.wordcard.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository<엔티티 타입, PK 타입>을 상속하면 기본 CRUD 메서드를 자동으로 제공
public interface WordRepository extends JpaRepository<Word, Long> {

    // 최신순(createdAt 내림차순) 전체 조회
    List<Word> findAllByOrderByCreatedAtDesc();

    // 알파벳순(word 오름차순) 전체 조회
    List<Word> findAllByOrderByWordAsc();

    // 단어 또는 뜻으로 검색 + 최신순
    // 메서드 이름이 길지만, JPA가 이름을 분석해서 SQL을 자동으로 만들어줌
    List<Word> findByWordContainingIgnoreCaseOrMeaningContainingIgnoreCaseOrderByCreatedAtDesc(
            String word, String meaning);

    // 단어 또는 뜻으로 검색 + 알파벳순
    List<Word> findByWordContainingIgnoreCaseOrMeaningContainingIgnoreCaseOrderByWordAsc(
            String word, String meaning);
}
