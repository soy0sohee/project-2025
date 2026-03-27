package com.study.Ex19AssoMapping.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor //기본생성자 - 스프링빈생성(Jackson Lib) 오류
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    //mappedBy: 연관관계 주인은 내가 아님 -> comment가 주인 / FK(외래키)를 가진 쪽이 연관관계의 주인
    //cascade: 부모-자식 엔티티의 생명주기를 함께 관리
    //       : 부모(Board)에 일어나는 일이 자식(Comment)에게도 전파되도록 함
    //  -> CascadeType.ALL: 모든 동작 포함
    //  -> PERSIST(저장), REMOVE(삭제), MERGE(수정), REFRESH(새로고침), DETACH(영속성분리)
    //orphanRemoval: 부모(board)와 관계가 끊어지면, 자식(comment) 자동 삭제
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default //빌더패턴을 사용할 때, comments가 null아 아니고 빈리스트를 유지하도록 해줌
    private List<Comment> comments = new ArrayList<>();
}
