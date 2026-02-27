package com.study.Ex09Model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 스프링 프론트 컨트롤러 클래스가 여러개 있을 수 있음
// MainController
// LoginController
// QnAConroller
// 단, 경로만 겹치지 않게 하면 됨, 핸들러 맵퍼가 알아서 라우팅함(URL이 들어오면, 어떤 컨드롤러 메서드를 실행할지 자동으로 찾아줌)
// Git 충돌방지: 같은 파일을 동시에 편집하지 않는다.

@Controller
public class ParamController {
    @GetMapping("/main")
    public String main(){
        return "redirect:loginForm";
    }
    // redirect : 응답 헤더에 리다이렉트 할 주소를 넣어서 응답 -> 웹브라우저가 주소를 보고 다시 요청함
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
    @PostMapping("/loginAction1")
    public String loginAction1(HttpServletRequest req, Model model){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username = "+username);
        System.out.println("password = "+password);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "loginResult";
    }
    @PostMapping("/loginAction4")
    public String loginAction4(String username, @RequestParam("password") String pw, Model model){
        model.addAttribute("username", username);
        model.addAttribute("password", pw);
        return "loginResult";
    }
    @PostMapping("/loginAction2")
    public String loginAction2(Member member, Model model){
        model.addAttribute("member", member);
        return "loginResult2";
    }
    // @RequestParam : 클라이언트가 보낸 요청 파라미터를 컨트롤러 메서드의 변수에 1:1 매핑해주는 애너테이션
    @PostMapping("/loginAction3")
    public String loginAction3(@RequestParam Map<String, Object> map, Model model){
        model.addAttribute("map", map);
        return "loginResult3";
    }

    // @RequestMapping : 어떤 타입의 요청도 모두 받음
    // @PathVariable : 호출 경로를 매개변수로 받을 수 있음
    // HTML을 거치지 않고 바로 불러옴
    // URL : localhost:8080/loginAction1/hong/1234
    @RequestMapping("/loginAction1/{pathvar1}/{pathvar2}")
    public String loginAction1(@PathVariable("pathvar1") String id, @PathVariable("pathvar2") String pw, Model model){
        model.addAttribute("username", id);
        model.addAttribute("password", pw);
        return "loginResult";
    }
}
