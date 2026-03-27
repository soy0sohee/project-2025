package com.study.Ex19AssoMapping.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    //private Long board_id; -> 게시글 id - 외래키(FK), 기존 방식

    //fetch: 데이터를 언제 조회할지(조회 전략)
    //  -> FetchType.LAZY: 지연, 나중에 필요할 때 조회
    //                   : SELECT 2번 사용, Join 대신 SELECT로 바꾸는 전략
    //  -> FetchType.EAGER: 즉시, 처음부터 같이 조회
    //                    : 상황에 따라 Join or SELECT 사용
    @ManyToOne(fetch = FetchType.LAZY)
    //board_id라는 FK 컬럼을 만듦
    @JoinColumn(name="board_id")
    private Board board; //board 엔티티의 객체를 매핑
}

//댓글을 조회하려면, 게시글의 id를 fk필드로 가진 레코드를 검색해야했음
//SELECT * FROM comment WHERE board_id = 3;
