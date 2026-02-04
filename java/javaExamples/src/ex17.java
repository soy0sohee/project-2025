// 클래스를 또 선언하려면, 클래스 밖에서 선언
public class ex17 {
    // 함수를 또 선언하려면, 함수 밖에서 선언
    public static void main(String[] args) {
        // 함수 : 어떤 일을 수행하는 코드 묶음
        // 메서드 : 클래스 안에 들어있는 함수
        //        : 자바에서는 함수를 혼자 못 쓰고 반드시 클래스 안에 둠
        //        : 자바에서는 함수를 전부 메서드라고 부름

        // main : 실행만 하는 곳
        //      : 메서드, 클래스 선언 불가
        //      : 오직 호출과 결과만 사용
        myFunc1();
        myFunc2(10);
        int result = myFunc3();
        System.out.println("result = " + result);
        String resultStr =  myFunc4("대한", "민국");
        System.out.println("resultStr = " + resultStr);
    }

    // static 함수 : 객체를 만들지 않고 클래스 이름으로 바로 호출 가능한 메서드
    // void 타입 : 반환값이 없는 타입

    // static 함수에서 함수 호출 시 static 사용
    // 매개변수 x, 반환값 x (void 타입 사용)
    static void myFunc1() {
        System.out.println("myFunc1 호출됨.");
        return; // 반환값 없을 때 생략 가능
    }

    // 매개변수 o, 반환값 x
    // 함수의 매개변수와 지역변수는 함수 안에서만 작동한다.
    static void myFunc2 (int param) { // int param = 외부에서 받은 값
        int localVar = 20; // int localvar = 메서드 안에서 만든 값
        System.out.println("myFunc2:" + param);
    }

    // 매개변수 x, 반환값 o
    static int myFunc3() {
        //반환타입과 값을 일치시켜야 됨.
        System.out.println("myFunc3 호출됨.");
        return 30;
    }

    //매개변수 O, 반환값 O
    static String myFunc4(String param1, String param2){
        return param1 + param2;
    }
}

class Other {
    //클래스 밖에 선언
}

