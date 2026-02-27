package com.study.Ex15Board.controller;

import com.study.Ex15Board.dto.ReplySaveRequestDto;
import com.study.Ex15Board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    // 생성자주입
    private final ReplyService replyService;

    // 댓글 삭제
    @GetMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam Long replyIdx, @RequestParam Long boardIdx){
        replyService.delete(replyIdx);
        return "<script>alert('삭제 성공'); location.href='/board/contentForm?boardIdx="+boardIdx+"';</script>";
    }

    // 댓글 쓰기
    @PostMapping("/writeReplyAction")
    @ResponseBody
    public String writeReplyAction(ReplySaveRequestDto dto, @RequestParam Long replyBoardIdx){
        Long newReplyIdx = replyService.save(dto);

        boolean isFound = replyService.existsById(newReplyIdx);
        if(isFound){
            return "<script>alert('댓글등록 성공'); location.href='/board/contentForm?boardIdx="+replyBoardIdx+" ';</script>";

        }else{
            return "<script>alert('삭제 에러'); history.back();</script>";

        }
    }
}
