package com.study.Ex03SpringDI;

// 회원정보를 담는 클래스
// POJO
// : 순수한 자바클래스에 getter/setter만 있는 클래스
// : 스프링이 관리하는 빈 클래스
// : EJB 클래스와는 다르게 단순하고 관리가 편함
// : Plain Old Java Object의 약자

import org.springframework.stereotype.Component;

@Component // 스프링 Member 빈으로 자동 생성
public class Member {
    private String name = "강감찬";

    // 기본 생성자
    // Spring 프레임워크는 @Component가 붙은 클래스를 발견하면 리플렉션(클래스 정보를 파악해서 객체를 생성하는 기술)을 실행함
    // 리플렉션 => 클래스 경로를 읽어오는데 기본적으로 매개변수가 없는 생성자 함수를 호출
    //          => 자바의 몰래보기 기능
    //          => 클래스 이름만 알고 있어도, 안에 뭐가 있는지 조사하고 호출 실행하는 기술
    // 자바의 규칙에서 필드가 있는 생성자를 하나라도 직접 정의하면, 컴파일러가 자동으로 생성하던 기본 생성자를 제공하지 않음
    // 이때, NoSuchMethodException(존재하지 않는 메서드를 호출하려고 할 때 발생하는 예외)이 발생

    // 즉, @Component에는 생성자가 없으면 자바 컴파일러가 기본 생성자 자동 생성,
    // 생성자 하나는 문제가 없지만 여러 생성자가 있을때는 @Autowried를 붙여 기본으로 호출할 아이를 정해줘야함

    // ✔ 생성자 자동 생성 → 자바 역할
    // ✔ 생성자 호출 → Spring이 리플렉션 사용
    // ✔ 생성자 1개 → 자동 선택
    // ✔ 생성자 여러 개 → @Autowired 필요

    public Member() {
    }

    // 매개변수+필드가 있는 생성자
    public Member(String name) {
        this.name = name;
    }

    // Getter/Setter 함수
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
