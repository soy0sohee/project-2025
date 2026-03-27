package com.study.Ex18TDD;

//단정(Assert) 메서드 : 테스트 케이스의 수행 결과를 판별하는 용도로 사용.
//종류
//assertArrayEquals(a, b) : 배열 a와 b가 일치함을 확인한다.
//assertEquals(a, b) : 객체 a와 b가 일치함을 확인한다. (객체에 정의되어 있는 equals를 통해 비교한다.)
//assertSame(a, b) : 객체 a와 b 가 같은 객체임을 확인 한다. (객체 자체를 비교한다. == )
//assertTrue(a) : 조건 a가 참인지를 확인한다.
//assertFalse(a) : 조건 a가 거짓인지를 확인한다.
//assertNotNull(a) : 객체 a가 null인지 확인한다.
//assertAll() : 모든 종류의 assert를 각각 다 실행한다. 중간에 멈추지 않음.

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// 클래스와 메서드의 기능 단위 테스트
class Adder {
    public int add(int x, int y) {
        return x + y;
    }
}

public class AssertTest {
    private final String[] arr1 = {"a", "b"};
    private final String[] arr2 = {"a", "c"};
    private final Adder adder1 = new Adder();
    private final Adder adder2 = new Adder();

    // @Test: 테스트 케이스 함수임을 알려주는 어노테이션
    //      : 테스트 케이스 단독 실행 가능
    @Test
    void test1() {
        System.out.println("test1 테스트 시작!");

        // 두 개의 배열의 내용을 비교
        // 내용이 다르면, assert 단정(실행 중단)
        assertArrayEquals(arr1, arr2, "배열 값이 서로 다릅니다.");

        System.out.println("test1 테스트 종료!");
    }

    @Test
    void test2() {
        System.out.println("test2 테스트 시작!");

        assertEquals(3, adder1.add(1,2), "add결과가 다릅니다.");

        System.out.println("test2 테스트 종료!");
    }

    @Test
    void test3() {
        // == 비교: 주소값을 비교, 같은 객체인가
        assertSame(adder1, adder1, "1. 서로 다른 객체입니다.");
        assertSame(adder1, adder2, "2. 서로 다른 객체입니다.");
    }

    @Test
    void test4() {
        // 조건이 true면 통과, false면 중단
        assertTrue(3<10, "1. 조건이 다릅니다.");
        assertTrue(3>10, "2. 조건이 다릅니다.");

        // 조건이 false면 통과, true면 중단
        assertFalse(3>10, "1. 조건이 같습니다.");
        assertFalse(3<10, "2. 조건이 같습니다.");
    }

    @Test
    void test5() {
        // 객체가 null이면 통과, null이 아니면 중단
        assertNull(adder1, "1. 객체가 null이 아닙니다.");
        assertNull(null, "2. 객체가 null이 아닙니다.");
    }

    @Test
    void test6() {
        // 객체가 null이 아니면 통과, null이면 중단
        assertNotNull(adder1, "1. 객체가 null 입니다.");
        assertNotNull(null, "2. 객체가 null 입니다.");
    }

    @Test
    void test7() {
        assertAll(
                // 람다식 지원
                ()->{assertTrue(true,"1. 조건이 거짓입니다.");},
                ()->{
                    Object obj = new Object();
                    assertNotNull(obj,"1. 객체가 null 입니다.");
                    assertNotNull(null,"2. 객체가 null 입니다.");
                }
        );
    }
}
