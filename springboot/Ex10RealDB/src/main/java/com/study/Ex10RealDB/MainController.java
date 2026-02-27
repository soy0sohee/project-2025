package com.study.Ex10RealDB;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자 주입으로 Repository 객체 가져오기
public class MainController {
    // DB호출한 인터페이스 생성자 주입
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String main(Model model){

        // Repository 호출
        // MemberEntity 클래스 타입으로 list를 받음
        List<MemberEntity> list = memberRepository.findAll(); // Select * SQL 실행

        System.out.println("list.size() = " + list.size());
        model.addAttribute("list", list);

        return "index";
    }
}
