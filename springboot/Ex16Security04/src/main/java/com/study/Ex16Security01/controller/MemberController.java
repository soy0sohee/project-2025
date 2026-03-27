package com.study.Ex16Security01.controller;

import com.study.Ex16Security01.dto.MemberJoinDto;
import com.study.Ex16Security01.dto.MemberUpdateDto;
import com.study.Ex16Security01.serivce.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// 프론트 -> http요청 -> controller -> service -> DTO -> repository -> entity -> DBMS
// DBMS -> entity -> repository -> service -> DTO -> controller -> (view resolver -> html변환) -> 타임리프 -> html응답
// M                               C                        V
// DBMS -> entity -> repository -> service -> controller -> DTO -> (dto -> json문자열 변환(시리얼라이즈)) -> http body응답
// M                               C                        V

// 회원(로그인,회원가입) 요청 처리
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 인증되지 않은 사용자여도, 로그인/회원가입 페이지로 접근 가능해야 함
    // 시큐리티 적용후에는 권한이 없어서 안됨
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinFormValid";
    }

    // HTTP 데이터를 받는 방법
    // 1. @RequestParam : 직접 값을 하나씩 전달 받을때
    //    -> 형식 : application/x-www-form-urlencoded
    //    -> 모양 : name=hong&age=20
    // 2. @RequestBody : DTO 객체로 한번에 전달 받을때(JS / POST 가능)
    //    -> 형식 : application/json
    //    -> 모양 : {"name"="hong","age"=20}
    // 3. @ModelAttribute : DTO 객체로 한번에 전달 받을때(HTML-Form / GET,POST 가능)
    //    -> 형식 : 클래스, 맵 바인딩

    // HTTP 데이터 응답하는 방법
    // 1. return "html파일 이름" : 타임리프나 JSP 동적 HTML(뷰템플릿)
    // 2. @ResponseBody : 문자열이나 JS, JSON, XML 문자열로 반환

    @PostMapping("/joinAction")
    public String joinAction(@Valid MemberJoinDto dto, BindingResult bindingResult) {
        boolean result = memberService.joinAction(dto, bindingResult);
        if (!result) {
            // 유효성 실패 시 회원가입 화면으로 되돌아감
            return "redirect:/join";
        }
        return "redirect:/login";
    }

    @GetMapping("/viewDTO")
    public String viewDTO(@RequestParam Long id, Model model) throws Exception {
        // 타임리프 html에 데이터 넘길 때, 쓰는 객체
        // 1. Model : 순수하게 뷰템플릿에 데이터 바인딩
        // 2. Request : Model 기능 + HTTP 요청에 대한 정보(헤더, 바디)를 가짐
        // 3. session : X -> 로그인부터 로그아웃까지의 데이터(로그인여부, 로그인아이디, 프로필이미지 등) 보관
        // 4. application : X -> 웹브라우저 닫을때까지 데이터(쿠키, 로컬스토리지 등) 보관, 전체 앱에 사용할 때
        MemberUpdateDto dto = memberService.getDto(id);
        model.addAttribute("member", dto);
        return "modifyForm";  // 수정폼에는 데이터가 들어있어야 편함
    }

    @PostMapping("/modifyAction")
    @ResponseBody
    public String modifyAction(@ModelAttribute MemberUpdateDto dto) {
        System.out.println(" >> 바인딩: " + dto.getUsername());
        // 반환값
        // 1. boolean : 성공여부
        // 2. int : 여러상태를 알기 위해
        boolean isOk = memberService.modifyAction(dto);
        if (isOk) {
            return "<script> alert('회원정보 수정 성공!'); location.href='/admin'; </script>";
        } else {
            // history.back() : 유저가 쓴 정보가 유실되지 않는다.
            return "<script> alert('회원정보 수정 실패!'); history.back(); </script>";
        }
    }

    @GetMapping("/deleteDTO")
    @ResponseBody
    public String deleteDto(@RequestParam Long id) {
        boolean isOk = memberService.deleteDto(id);
        if (isOk) {
            return "<script> alert('회원정보 삭제 완료'); location.href='/admin'; </script>";
        } else {
            return "<script> alert('회원정보 삭제 실패'); history.back(); </script>";
        }
    }
}
