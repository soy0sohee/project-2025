package com.study.Ex18TDD;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcTest {
    static Calc calc = null;
    
    // 테스트 케이스 실행 전 처음 한번만 호출
    @BeforeAll
    static void init() {
        System.out.println(":: init() 실행");
        calc = new Calc();
    }

    @Test
    // 테스트 메서도 이름
    @DisplayName("add 함수 테스트")
    void add() {
        assertEquals(12, calc.add(10, 2), "add 결과값 오류");
    }

    @Test
    @DisplayName("sub 함수 테스트")
    void sub() {
        assertEquals(8, calc.sub(10, 2), "mul 결과값 오류");
    }

    @Test
    @DisplayName("mul 함수 테스트")
    void mul() {
        assertEquals(20, calc.mul(10, 2), "mul 결과값 오류");
    }

    @Test
    @DisplayName("div 함수 테스트")
    void div() {
        assertEquals(5, calc.div(10, 2), "div 결과값 오류");
    }
}