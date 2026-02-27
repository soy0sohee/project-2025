// 다형성(폴리모피즘)
// : 자식 클래스 객체가 자기 클래스 또는 부모 클래스에 타입을 모두 가질 수 있는 것
// : 스프링 설계 철학에 관여됨
// 사용하는 이유
// : 타입의 유연성을 갖기 위해
// : 타입을 정확하게 지정하지 않더라도 객체를 매개변수로 전달이 가능함
// : 프로그램 기능을 확장할 때, 기존 코드 유지한채로 새로운 코드를 추가하기 쉬움
// 예) USB C타입 호환 : 어떤 USB C타입 기기도 사용가능

class MonoCalss {
    int mono(int price){
        return price * 2;
    }
}

class Parent {
    String name = "parent";
    void parentMethod(){
        System.out.println("parent 내 이름은 " + this.name);
    }
}
class Child extends Parent {
    String name = "child";
    void childMethod(){
        System.out.println("child 내 이름은 " + this.name);
    }
}

public class ex34 {
    public static void main(String[] args) {
        Parent p1 = new Parent();
        System.out.println(p1.name);
        Child c1 = new Child();
        System.out.println(c1.name);
        
        // 1. 업캐스팅(Up Casting) : 자식객체가 부모클래스의 타입을 갖는것
        // -> 대입연산자를 통해서 가능(암묵적 형변환)
        Parent p2 = new Child();
        System.out.println(p2.name);
        // -> 강제형변환 연산자를 통해서 가능(명시적)
        Parent p3 = (Parent)c1;
        System.out.println(p3.name);

        // 2. 다운캐스팅(Down Casting) : 자식객체가 자식클래스의 타입을 갖는것
        Child c2 = (Child)p2;
        System.out.println(c2.name);

        // 4가지 패턴
        Parent a = new Parent();
        Child b = new Child();
        Parent c = new Child();
        Child d = (Child) c;
    }
}
