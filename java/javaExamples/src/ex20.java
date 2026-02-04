// Java this = 객체 자신을 이야기 함
class BallFactory {
    int ballCount = 100;

    void make() {
        this.ballCount++; //Java에서 this는 단순하게 객체 자신을 의미함.
        System.out.println("축구공 생산!");
    }
}

//일반클래스는 new해야 사용가능, 다른 파일(폴더)에 있다면, import해야됨.
//클래스 안의 정적 변수(함수)는 new가 필요없음.
class FoodFactory {
    static int foodCount = 200;

    static void make() {
        foodCount++;
        System.out.println("도시락 생산!");
    }
}

public class ex20 {
    public static void main(String[] args) {
        // 문자열 1차배열
        String[] strings = {"사과", "배", "감귤"};
        System.out.println(strings[0]);

        // static 예약어
        // non-static 변수 : 일반변수 -> Heap영역(희발성)
        //                 : 로컬변수 -> Stack영역(희발성)
        // static     변수 : 정적 변수(주소값이 고정) -> Static영역(메서드(코드)영역)

        // 사용하는 이유
        // 1. 시작점(Entry Point)를 지정할 때 사용
        // 2. 중요한 데이터를 안정적으로 저장할 때
        //    전역변수(함수) = 프로그램 전체에서 접근 가으한 변수/함수
        // 3. 자주 사용하는 유틸성 클래스에 지정
        //    예) Math.abs() import나 new를 안해도 되는 편리함
        
        BallFactory ballFactory = new BallFactory();
        ballFactory.make();
        System.out.println(ballFactory.ballCount);
        
        // static 변수/함수는 new를 안해도 됨. 편리함
        // but 모든 변수/함수에 static을 쓴다면?
        // 객체 지향 설계 철학 중 은닉에 반함 -> 접근 주체를 감지하기 어려움
        // C언어에서 모든 변수가 전역 변수였음 -> 버그 잡기 어려움
        System.out.println(FoodFactory.foodCount);
        FoodFactory.make();
        System.out.println(Math.abs(-10));
    }
}


