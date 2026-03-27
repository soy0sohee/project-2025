package com.study.ExPostit;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "memo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Postit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String color = "yellow";
    @Column(name="pos_x")
    private Integer posX;
    @Column(name="pos_y")
    private Integer posY;
    @Column(name="z_index")
    private Integer zIndex;
    private Double rotation;
    @CreationTimestamp
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDateTime updatedAt;



    // glue 색상 (진한 색)
    public String getGlueColor() {
        if (this.color == null) return "#FFF176"; // ← 이거 추가
        return switch (this.color) {
            case "yellow" -> "#FFF176";
            case "orange" -> "#FFB74D";
            case "pink"   -> "#F06292";
            case "green"  -> "#A5D6A7";
            case "blue"   -> "#64B5F6";
            case "purple" -> "#BA68C8";
            case "white"  -> "#EEEEEE";
            default       -> "#FFF176";
        };
    }

    // 메모 배경색 (연한 색)
    public String getBgColor() {
        if (this.color == null) return "#FFF59D";  // ← 이거만 추가
        return switch (this.color) {
            case "yellow" -> "#FFF59D";
            case "orange" -> "#FFCC80";
            case "pink"   -> "#F48FB1";
            case "green"  -> "#C5E1A5";
            case "blue"   -> "#90CAF9";
            case "purple" -> "#CE93D8";
            case "white"  -> "#FAFAFA";
            default       -> "#FFF59D";
        };
    }
}
