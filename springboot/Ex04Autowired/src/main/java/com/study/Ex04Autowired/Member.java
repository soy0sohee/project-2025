package com.study.Ex04Autowired;

import org.springframework.stereotype.Component;

@Component("member")
public class Member {
    private String name;
    private ICard icard; // 확장성을 고려한 다형성 설계, 다른 카드가 추가될 수 있음

    // 생성자
    // : JAVA 컴파일러는 기본생성자 자동생성 / 필드생성자가 있으면 -> 기본생성자 자동생성 안 함
    // : Spring은 기본생성자 자동생성 안 함
    // : 빈으로 만든 클래스의 기본생성자를 기본으로 반드는 것이 좋음
    public Member(){}

    public Member(String name, ICard icard) {
        this.name = name;
        this.icard = icard;
    }
    // getter/setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ICard getIcard() {
        return icard;
    }

    public void setIcard(ICard icard) {
        this.icard = icard;
    }
}
