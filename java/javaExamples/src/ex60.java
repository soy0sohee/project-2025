// 람다식 : 메서드를 간단하게 표현하는 방법, 일명 이름 없는 함수
// : JS의 화살표함수(함수형변수)와 유사한 역할
// : JDK 1.8부터 지원
// : 함수형 언어를 지원하기 위해 만들어진 기능
// : 인터페이스 문법을 차용함

// 람다식 선언을 위한 어노테이션
@FunctionalInterface
// 인터페이스 상수 선언
interface MyFunc1 {
    int sum(int x, int y); // 단 하나의 메서드만 선언, 추상 메서드
}
// 구현 class 선언
class MyFunc1IMPL implements MyFunc1 {
    @Override
    public int sum(int x, int y) {
        return x + y;
    }
}
public class ex60 {
    public static void main(String[] args) {
        // 인터페이스 방식
        // 구현 클래스 호출
        MyFunc1IMPL f2 = new MyFunc1IMPL();
        System.out.println(f2.sum(20,30)); // 구현

        // 익명함수 방식 -> 인터페이스를 호출하지 않고 바로 함수식으로 사용 가능
        // 구현 함수(람다식 객체) 선언
        MyFunc1 f1 = (x,y) -> {return x+y;}; // () -> {} 사용
        System.out.println(f1.sum(10,20)); // 구현

        execFunc(f1); // 일반함수 사용
    } // main
    static void execFunc (MyFunc1 func1){
        System.out.println(func1.sum(30,40));
    }
} // class
