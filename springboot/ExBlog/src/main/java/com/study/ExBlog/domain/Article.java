package com.study.ExBlog.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)  // JPA 엔티티의 생성/수정 시간을 자동으로 관리하기 위해 사용하는 설정
@Entity
@Table(name="article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 접근제어자를 protected로 설정
@AllArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", updatable=false)  // updatable=false : 컬럼의 업데이트 가능 여부 설정 옵션, AUTO_INCREMENT가 있음으로 업데이트 생략
    private Long id;

    @Column(name="title", nullable=false)
    private String title;

    @Column(name="content", nullable=false)
    private String content;

    @CreatedDate  // entity가 생성될 때 생성 시간을 컬럼에 저장
    @Column(name="created_at", updatable=false)
    private LocalDateTime createdAt;

    @LastModifiedDate  // entity가 수정될 때 수정 시간을 컬럼에 저장
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    // 빌더 패턴으로 객체 생성
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Entity -> DTO
    public void toUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
