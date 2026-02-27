package com.study.Ex12LoginJoinDB.controller;

import com.study.Ex12LoginJoinDB.dto.MemberLoginDto;
import com.study.Ex12LoginJoinDB.dto.MemberSaveDto;
import com.study.Ex12LoginJoinDB.entity.MemberEntity;
import com.study.Ex12LoginJoinDB.entity.MemberRepository;
import com.study.Ex12LoginJoinDB.service.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final LoginService loginService;

    // 메인
    @GetMapping("/")
    public String index(){
        // session 객체의 정보는 유효함
        return "index";
    }

    // 로그인
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    // @Valid : Validation(유효성 체크) 애너테이션
    // BindingResult : 밸리데이션 결과값을 가진 객체
    // 로그인 값 처리 + 유효성 검사
    @PostMapping("/loginAction")
    @ResponseBody // 문자열 반환
    public String loginAction(@Valid @ModelAttribute MemberLoginDto dto,
                              BindingResult bindingResult,
                              HttpSession session){
        if(bindingResult.hasErrors()){
            // DTO에 설정한 메시지값을 가져온다
            String detail = bindingResult.getFieldError().getDefaultMessage();
            System.out.println("detail = " + detail);

            // 스프링 서버에서 자바스크립트를 body에 응답값으로 보내면
            // 웹브라우저가 자바스크립트를 수행해 줌
            // history.back() : 로그인(회원가입)시 유저가 입력한 값이 그래도 남아있음
            return "<script>alert('" + detail + "');history.back();</script>";
        }

        // 아이디, 패스워드 체크 로직
        // 로그인 성공 로직
        session.setAttribute("isLogin", true);
        session.setAttribute("userId", dto.getUserId());
        // 프론트 컨트롤러의 코드가 길어지면 -> Service 클래스로 코드 분리(MVC 패턴)
        String userRole = loginService.getUserRole(dto.getUserId());
        session.setAttribute("userRole", userRole);
        System.out.println(userRole);

        if(userRole.equals("ROLE_ADMIN")){
            return "<script>alert('로그인 성공');location.href='/admin';</script>";
        } else {
            return "<script>alert('로그인 성공');location.href='/';</script>";
        }
    }

    @GetMapping("/logoutAction")
    public String logoutAction(HttpSession session){
        session.invalidate(); //로그아웃 처리
        return "redirect:/";
    }

    // 회원목록
    @RequestMapping("/admin")
    public String admin(Model model){
        List<MemberEntity> list = memberRepository.findAll();
        model.addAttribute("list",list);
        return "admin";
    }
    // 리다이렉트 : a태그, location.href, meta refresh
    // - request, model 데이터가 날라감

    // 비회원 : 로그인 안한 사용자 -> 쿠키에 사용자 흔적을 담는다.

    // 회원가입
    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    // 회원가입 완료
    // @ModelAttribute : 클라이언트가 보낸 HTTP 요청 파라미터를 자바 클래스에 매핑하는 어노테이션
    @PostMapping("/joinAction")
    public String joinAction(@ModelAttribute MemberSaveDto dto){
        System.out.println("id = " + dto.getUserId());
        System.out.println("name = " + dto.getUserName());

        dto.setJoinDate(LocalDate.now()); // 현재 날짜로 설정

        try{
            MemberEntity entity = dto.toSaveEntity();
            memberRepository.save(entity);
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/";
        }

        return "redirect:/";
    }

    // 단건조회
    @GetMapping("/viewMember")
    // viewMember?id=${member.id}
    public String viewMember(@RequestParam("id") Long id, Model model){
        System.out.println("id = " + id);
        Optional<MemberEntity> optional = memberRepository.findById(id);
        if(!optional.isPresent()){
            return "redirect:/";
        }
        // null이 아니면 람다식 실행
        optional.ifPresent((entity)->{
            model.addAttribute("member",entity.toSaveDto());
        });
        return "modifyForm";
    }

    // 수정
    @PostMapping("/modifyAction")
    public String modifyAction(@ModelAttribute MemberSaveDto dto){
        try{
            MemberEntity entity = dto.toSaveEntity();
            memberRepository.save(entity);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }

    // 삭제
    @GetMapping("/deleteMember")
    public String deleteMember(@RequestParam("id") Long id){
        try{
            memberRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
