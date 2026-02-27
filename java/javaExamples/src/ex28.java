// 소프트웨어 개발/유지보수 어려움(방법론, 공학)
// 코드 중복 이슈 : 같은 기능의 코드가 10개 있다면?
// -> 고객의 요청이나 서비스 상황에 따라서 자주 변경됨
// -> 수정 후 사이드이펙트(하나의 코드를 수정하면, 다른 코드가 영향을 받음)
// -> 코드가 모듈 형태로 분리되지 않는 문제로 발생!
// 코드 중복을 줄이고, 사이드이펙트(코드 독립성)를 줄이는 방향 설계
// 객체 지향 프로그래밍(OOP)

// 코드 중복 줄이는 방법
// 1. 반복문이나 배열/리스트 사용
// 2. 함수 사용
// 3. 클래스(변수+함수) 사용
// 4. 클래스 상속 이용(공통+단일 분리)
// 5. 다형성 - 상속관계 타입의 유연성
// 6. 제네틱 - 모든 타입의 유연성
// 7. 추상화클래스(인터페이스) - 가상함수+메서드 오버라이드

// 클래스의 상속
// : 부모(상위) 클래스의 유산(변수,함수)을 자식(하위)클래스가 물려받는 것
// 사용하는 이유
// 1. 코드 중복 제거
// 2. 계층적인 구조로 코드 설계
// 예) 강아지 : 동물 속성/행동 + 강아지 고유의 속성/행동
//     고양이 : 동물 속성/행동 + 고양이 고유의 속성/행동

class Animal {
    int age = 10;
    void eat() {
        System.out.println("먹는다.");
    }
}
class Dog extends Animal {
    void bark() {
        System.out.println("짖다");
    }
}
class Cat extends Animal {
    void grooming() {
        System.out.println("핥다");
    }
}

public class ex28 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog.age);
        dog.eat();
        dog.bark();
    }
}
