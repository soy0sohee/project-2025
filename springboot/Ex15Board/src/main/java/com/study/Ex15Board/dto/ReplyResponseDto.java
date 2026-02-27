package com.study.Ex15Board.dto;

import com.study.Ex15Board.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {
    private Long replyIdx;
    private String replyName;
    private String replyContent;
    private LocalDateTime replyDate;
    private Long replyBoardIdx;

    // Entity to Dto
    public ReplyResponseDto(Reply entity){
        this.replyIdx = entity.getReplyIdx();
        this.replyName = entity.getReplyName();
        this.replyContent = entity.getReplyContent();
        this.replyDate = entity.getReplyDate();
        this.replyBoardIdx = entity.getReplyBoardIdx();
    }
}
