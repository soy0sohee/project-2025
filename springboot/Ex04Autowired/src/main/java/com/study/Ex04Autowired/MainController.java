package com.study.Ex04Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/")
    @ResponseBody
    public String main(){
        return "스프링부트 웹서버 연결됨";
    }

    // 필드 주입으로 Member 객체 사용하기
    @Autowired
    private Member member;

    @GetMapping("/member")
    @ResponseBody
    public String member(){
        member.setName("이순신");
        System.out.println("member.getName() = " + member.getName());
        return member.getName();
    }

    // 인터페이스 오토와이어링 불가, ICard의 구현객체가 2개이므로 선택 필요
    // 인터페이스 구현객체 선택 방법
    // @Qualifier
    // @Primary
    @Autowired
    @Qualifier("bCCard")
    ICard iCard;
    @GetMapping("/card")
    @ResponseBody
    public String card(){
        System.out.println("card() 호출됨");
        member.setName("홍길동");
        member.setIcard(iCard);
        member.getIcard().buy("핸드폰");
        return member.getName() + "님 " + member.getIcard() + "로 구매";
    }
}//class
