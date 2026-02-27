// 추상화 클래스와 인터페이스 차이

// 추상화 클래스(일반 클래스)는 다중상속 불가, 다단계 상속은 가능
class A {}
class B extends A {}
class C extends B {}
// class D extends A,B,C {} -> 다중상속 불가

// 인터페이스는 다중상속 가능
interface IA {}
interface IB {}
class IC implements IA, IB {}

class Superman extends C implements IA, IB{}

public class ex38 {
    public static void main(String[] args) {

    }
}
