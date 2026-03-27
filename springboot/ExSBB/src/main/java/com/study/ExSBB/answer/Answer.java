package com.study.ExSBB.answer;

import com.study.ExSBB.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT 속성
    private Integer id;

    @Column(columnDefinition = "TEXT") //content TEXT
    private String content;

    private LocalDateTime createDate;

    @ManyToOne //N:1 관계, 외래키 관계 생성(하나의 질문에 여러 답변을 남길 수 있음)
    private Question question;
}
