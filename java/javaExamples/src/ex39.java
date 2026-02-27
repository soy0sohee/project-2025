// 익명 객체(이름 없는 객체 - Anonymous Object)
// : 일회성 메서드(코드 뭉치) 재정의할 때 사용
// : 한번 쓰고 버릴 코드에 굳이 이름을 붙여서 사용할 필요가 없음

interface NormalCar {
    void run();
}

class SuperCar implements NormalCar {
    @Override
    public void run() {
        System.out.println("슈퍼카가 달림");
    }
}

public class ex39 {
    public static void main() {
        SuperCar sc = new SuperCar();
        sc.run();

        // 일회성(익명) 인터페이스 객체
        NormalCar normalcar = new NormalCar() {
            public void run() {
                System.out.println("슈퍼카가 슝슝");
            }
        };

        normalcar.run();
    }
}
