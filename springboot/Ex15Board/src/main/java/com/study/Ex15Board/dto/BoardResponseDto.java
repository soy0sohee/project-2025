package com.study.Ex15Board.dto;

// RequestDto -> 폼(JSON) 데이터 요청
// ResponseDto -> 폼(JSON) 데이터 응답

// HTTP Request <-> Controller <-> DTO <-> Entity <-> Repository

import com.study.Ex15Board.domain.board.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {
    private Long boardIdx;
    private String boardName;
    private String boardTitle;
    private String boardContent;
    private Integer boardHit;
    private LocalDateTime boardDate;

    // entity -> DTO 생성자 함수
    public BoardResponseDto(Board entity){
        this.boardIdx = entity.getBoardIdx();
        this.boardName = entity.getBoardName();
        this.boardTitle = entity.getBoardTitle();
        this.boardContent = entity.getBoardContent();
        this.boardHit = entity.getBoardHit();
        this.boardDate = entity.getBoardDate();
    }
}
