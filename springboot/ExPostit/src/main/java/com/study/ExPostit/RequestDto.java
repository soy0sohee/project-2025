package com.study.ExPostit;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    private Long id;
    private String content;
    private String color;
    private Integer posX;
    private Integer posY;
    private Integer zIndex;
    private Double rotation;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public RequestDto(Postit e){
        this.id = getId();
        this.posX = getPosX();
        this.posY = getPosY();
        this.zIndex = getZIndex();
        this.rotation = getRotation();
    }
}
