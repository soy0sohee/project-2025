// 메서드 오버라이딩(Overriding)
// : 상속 관계에서 부모 클래스의 메서드를 자식 클래스에서 재정의하는 것
// : 부모 클래스의 메서드는 무시됨

class Cable {
    int price = 1000;
    void sale() {
        System.out.println("cable 20% 할인 판매");
    }
}

class PowerCable extends Cable {
    int price = 2000;
    @Override // 어노테이션 = 컴파일 지시어(생략가능)
    void sale() {
        System.out.println("PowerCable 15% 할인 판매");
    }
}

public class ex30 {
    public static void main(String[] args) {
        PowerCable powerCable = new PowerCable();
        System.out.println(powerCable.price); // 변수
        powerCable.sale(); // 메서드
    }
}
