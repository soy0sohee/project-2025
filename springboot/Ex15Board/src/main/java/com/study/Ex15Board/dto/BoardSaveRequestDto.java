package com.study.Ex15Board.dto;

import com.study.Ex15Board.domain.board.Board;
import lombok.*;

// DB 인덱스와 날짜는 기본 데이터를 사용
@Getter
@Setter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String boardName;
    private String boardTitle;
    private String boardContent;
    private Integer boardHit;

    @Builder
    public BoardSaveRequestDto(String boardName,
                               String boardTitle,
                               String boardContent,
                               Integer boardHit){
        this.boardName = boardName;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardHit = boardHit;
    }

    // DTO to Entity
    public Board toEntity(){
        return Board.builder()
                .boardName(boardName)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardHit(boardHit)
                .build();
    }
}
