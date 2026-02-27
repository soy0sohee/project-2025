// 인터페이스(interface)
// : 가상함수만 존재(일반함수X)

// 공통점
// 추상화 클래스와 인터페이스는 둘다 추상 메서드(가상 함수)를 가짐
// 설계와 구현의 관점을 둘다 가짐

// 차이점

// 추상화 클래스
// - 가상함수 있음
// - 일반함수 가능
// - 예약어 : abstract class / abstract 메서드
// - 상속어 : extends(상속)
// - 다중상속 불가 : extends a, b, c (여러명의 부모 불가)
// - 접근제한자 : public / protected / private
// - 필드선언 : 모두 가능

// 인터페이스
// - 가상함수 없음
// - 일반함수 불가
// - 예약어 : interface class / [abstract] 메서드
// - 상속어 : implement(구현)
// - 다중상속 가능 : implement a, b, c (여러명의 부모 가능)
// - 접근제한자 : public만 가능
// - 필드선언 : public / static만 가능

interface Drawing {
    abstract void draw();
    void sketch();
}

class Painter implements Drawing {
    @Override
    public void draw() {
        System.out.println("드로잉");
    }

    @Override
    public void sketch() {
        System.out.println("스케치");
    }
}

public class ex37 {
    public static void main(String[] args) {
        Painter painter = new Painter();
        painter.draw();
        painter.sketch();
    }
}
