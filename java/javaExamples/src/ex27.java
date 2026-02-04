// 연습문제
// 클래스명 : Desk
// 속성-필드(멤버변수) : color = "갈색"
// 행동-메서드(함수) : work() "일한다" 출력
// 생성자함수 : color = "흰색" 초기화

class Desk {
    // 필드
    String color = "갈색";

    // 생성자 함수
    public Desk(String color) {
        this.color = color;
        System.out.println(this.color);
        System.out.println("일한다");
    }

    // 생성자 함수 자동 생성
//    public Desk(String color) {
//        this.color = color;
//    }

    // Getter/Setter 자동 생성
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }

    // 메서드
    void work() {
        System.out.println("일한다");
    }
}

public class ex27 {
    public static void main(String[] args) {
        Desk desk = new Desk("흰색");
        System.out.println(desk.color);
    }
}
