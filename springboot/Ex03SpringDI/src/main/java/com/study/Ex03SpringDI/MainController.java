package com.study.Ex03SpringDI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @Controller
// : HTTP 요청을 가장 먼저 처리하는 컨트롤 클래스, 요청을 처리하는 클래스
// : GET /POST  /PUT      /PATCH /DELETE = 메서드를 처리
// : Read/Insert/UpdateAll/Update/Delete = DB액션
// : @Controller:mainController Bean으로 만듦
@Controller
public class MainController {
    // HTTP URL : "localhost:8080/"
    // "/" : Root 경로로 GET 요청을 받음
    @GetMapping("/") // GET(read) 요청을 처리하는 메서드 선언
    @ResponseBody // 응답을 html파일로 하지 않고, Body데이터(문자열)로 변환
    public String main() {
        return "스프링 부트 웹서버가 준비되었습니다.";
    }

    // HTTP URL : "localhost:8080/test"
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "테스트 경로입니다.";
    }
    
    // 1. 필드 주입
    // @Autowired : 스프링 빈을 생성(주입), 하나의 빈만 주입
    //            : Member member = new Member();를 대신해줌
    @Autowired
    private Member member1;
    @Autowired
    private Member member2;

    // HTTP URL : "localhost:8080/field"
    @GetMapping("/field")
    @ResponseBody
    public String field() {
        System.out.println(member2.getName());
        return "field() 호출됨 - member2";
    }

    // 2. 수정자 주입
    // : setter함수를 통해 주입
    // : 매개변수로 주입됨
    private Member member3;

    @Autowired
    public void setMember(Member member){
        System.out.println("수정자 주입됨");
        this.member3 = member;
    }

    @GetMapping("/setter")
    @ResponseBody
    public String setter() {
        System.out.println(member3.getName());
        return "setter() 호출됨 - member3";
    }

    // 3. 생성자 주입
    // : 1) final 키워드 사용 가능(객체 재할당 방지)
    // : 2) 생성자함수로서 미리 주입을 받을 수 있음(우선순위 높음)
    //      -> 객체A는 객체B가 있어야 생성되고 객체B는 객체A가 있어야 생성된다.(상호참조)
    // : 3) Null Safety 제공
    private final Member member4;

    @Autowired
    public MainController(Member member){
        System.out.println("생성자 주입됨"); // 수정자 주입 전에 실행
        this.member4 = member;
    }

    @GetMapping("/constructor")
    @ResponseBody
    public String constructor() {
        System.out.println(member4.getName());
        return "constructor 호출됨";
    }
} //class
