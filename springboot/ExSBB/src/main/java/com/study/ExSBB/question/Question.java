package com.study.ExSBB.question;

import com.study.ExSBB.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter //Setter 메서드는 안전하지 않으므로 사용하지 않기를 권함, 본 예제 예외 적용
public class Question {
    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT 속성
    private Integer id;

    @Column(length = 200) //Varchar(200)
    private String subject;

    @Column(columnDefinition = "TEXT") //content TEXT
    private String content;

    private LocalDateTime createDate;

    //mappedBy: 참조 엔티티의 속성명 정의
    //cascade: 현재 엔티티에 작업을 수행하면, 동일한 작업을 연결된 엔티티에도 적용
    //  -> CascadeType.REMOVE: 부모 엔티티에서 자식 엔티티로 삭제 작업 전달
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //1:N 관계, 외래키 관계 생성(하나의 질문에 여러 답변을 남길 수 있음)
    private List<Answer> answerList;
}
