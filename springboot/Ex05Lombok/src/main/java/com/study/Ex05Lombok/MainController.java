package com.study.Ex05Lombok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    // 필드 주입
    @Autowired
    private Member member; // 기본생성자로 생성된 객체가 주입됨
    @GetMapping("/")
    @ResponseBody
    public String main(){
        System.out.println("연결됨");

        // member.setName("홍길동");
        System.out.println("member.getName() = " + member.getName());
        System.out.println("member.getAge() = " + member.getAge());

        return member.getName() +" "+ member.getAge();
    }
    @GetMapping("/allArgs")
    @ResponseBody
    public String allArgs(){
        Member member = new Member("변사또", 30, "1234-1234", "satto_byoun");
        return member.getName() + " " + member.getAge() + " " + member.getPhone();
    }

    // 생성자 주입
    private final Member member1;
    @Autowired
    public MainController(Member member){ // 기본생성자로 생성된 객체가 주입됨
        this.member1 = member;
    }
    @GetMapping("/reqArgs")
    @ResponseBody
    public String reqArgs(){
        member1.setPhone("2345-1234");
        return "변경 번호 : " + member1.getPhone();
    }
}
