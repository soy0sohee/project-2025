package com.study.wordcard.service;

import com.study.wordcard.dto.WordRequestDto;
import com.study.wordcard.dto.WordResponseDto;
import com.study.wordcard.entity.Word;
import com.study.wordcard.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    // 단어 최대 개수
    private static final int MAX_WORDS = 10;

    // 단어 목록 조회 (검색어 + 정렬 조건에 따라 다른 메서드 호출)
    public List<WordResponseDto> getWords(String search, String sort) {
        List<Word> words;

        boolean hasSearch = search != null && !search.isBlank();
        boolean isAlphabet = "alphabet".equals(sort);

        if (hasSearch && isAlphabet) {
            words = wordRepository
                    .findByWordContainingIgnoreCaseOrMeaningContainingIgnoreCaseOrderByWordAsc(search, search);
        } else if (hasSearch) {
            words = wordRepository
                    .findByWordContainingIgnoreCaseOrMeaningContainingIgnoreCaseOrderByCreatedAtDesc(search, search);
        } else if (isAlphabet) {
            words = wordRepository.findAllByOrderByWordAsc();
        } else {
            words = wordRepository.findAllByOrderByCreatedAtDesc();
        }

        // Word 엔티티 목록을 WordResponseDto 목록으로 변환
        return words.stream()
                .map(WordResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단어 추가
    public WordResponseDto addWord(WordRequestDto requestDto) {
        if (wordRepository.count() >= MAX_WORDS) {
            throw new IllegalStateException("단어는 최대 " + MAX_WORDS + "개까지만 추가할 수 있습니다.");
        }

        Word newWord = new Word(requestDto.getWord(), requestDto.getMeaning());
        Word savedWord = wordRepository.save(newWord);
        return new WordResponseDto(savedWord);
    }

    // 단어 삭제
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }

    // 전체 단어 개수 조회
    public long getWordCount() {
        return wordRepository.count();
    }
}
