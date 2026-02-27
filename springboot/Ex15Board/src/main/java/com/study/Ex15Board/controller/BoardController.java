package com.study.Ex15Board.controller;

import com.study.Ex15Board.dto.BoardResponseDto;
import com.study.Ex15Board.dto.BoardSaveRequestDto;
import com.study.Ex15Board.dto.ReplyResponseDto;
import com.study.Ex15Board.service.BoardService;
import com.study.Ex15Board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board") // 시작하는 URL을 "/board"로 지정
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private final BoardService boardService;
    @Autowired
    private final ReplyService replyService;

    // 게시판 화면 호출
    @GetMapping("/")
    public String main(){
        return "redirect:/board/listForm";
    }

    // 리스트 DB 연동
    @GetMapping("/listForm")
    public String listForm(Model model){
        List<BoardResponseDto> list = boardService.findAll();

        model.addAttribute("list", list);
        return "listForm";
    }

    // 글작성 화면 호출
    @GetMapping("/writeForm")
    public String writeForm(){
        return "writeForm";
    }

    // HTTP 요청에서 보내주는 데이터 2가지
    // 1. Parameter Key-Value Data : RequestParam
    // 2. Body Raw Data : @RequestBody, @ModelAttribute
    // Spring 응답할 때
    // 1. @ResponseBody : HTTP body Raw Data(JSON,XML,자바스크립트)
    // 2. HTML파일 : View Resolver

    // 글작성 -> 글쓰기 버튼 액션
    @PostMapping("/writeAction")
    @ResponseBody
    public String writeAction(BoardSaveRequestDto dto){
        Long newIdx = boardService.save(dto);

        boolean isFound = boardService.existsById(newIdx);
        if(isFound){
            return "<script>alert('글쓰기 성공'); location.href='/board/listForm';</script>";
        } else {
            // 글쓰기 시 history.back() -> 유저가 입력한 내용을 기억함
            return "<script>alert('글쓰기 실패'); history.back();</script>";
        }
    }

    // 글작성 데이터 DTO 추가
    @GetMapping("/contentForm")
    public String contentForm(@RequestParam Long boardIdx, Model model){
        // 게시글 단건 조회
        BoardResponseDto dto = boardService.findById(boardIdx);
        // 조회수 증가 DB 업데이트
        boardService.updateHit(boardIdx, dto.getBoardHit() + 1);
        // 임시로 DTO에 설정
        dto.setBoardHit(dto.getBoardHit() + 1);

        // 댓글 목록 조회
        List<ReplyResponseDto> replyList = replyService.findAllByReplyBoardIdx(boardIdx);
        model.addAttribute("replyList", replyList);

        model.addAttribute("dto", dto);

        return "contentForm";
    }

    // 게시글 삭제
    @GetMapping("/deleteAction")
    @ResponseBody
    public String deleteAction(@RequestParam Long boardIdx){
        try{
            boardService.delete(boardIdx);
        }catch(Exception e){
            return "<script>alert('삭제 오류'); history.back();</script>";
        }
        return "<script>alert('삭제 성공'); location.href='/board/listForm';</script>";
    }

    // 게시글 수정
    @PostMapping("/updateAction")
    @ResponseBody
    public String updateAction(BoardSaveRequestDto dto, @RequestParam Long boardIdx){
        boolean isOK = boardService.update(boardIdx, dto);
        if(isOK){
            return "<script>alert('수정 성공'); location.href='/board/listForm';</script>";
        } else {
            return "<script>alert('수정 오류'); history.back();history.back();</script>";
        }
    }
}
