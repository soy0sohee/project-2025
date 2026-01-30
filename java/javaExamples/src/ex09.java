import java.util.Scanner;
// java.util.Scanner를 그냥 Scanner로 부를께 의미

// import란?
// 다른 패키지에 있는 클래스를 이름만으로 쓰게 해주는 문자
// import하는 이유?
// 컴파일 또는 번역, 패키징할 때 관련 코드를 포함 시킴
// -> 실행 파일 크기를 최소화하기 위해서

// import java.util.Scanner; : Scanner 클래스 하나만 import
// import java.util.*; : 패키지 전체 import

// 자동 import 클래스 패키지
// java.lang 패키지 = String, System, Math, Integer

public class ex09 {
    public static void main(String[] args) {
        // Scanner 클래스 함수 = 콘솔로 문자열 입력 받을 때 사용하는 클래스 함수
        // nextLine() : 전체 문자열 반환
        // next() : 공백 전 문자열 반환
        // nextInt() : 문자열을 받아서 정수로 반환
        // nextFloat() :               실수로 반환
        // nextDouble() :              실수로 반환

        // 클래스명 객체(인스턴스)명 = new 클래스명(매개변수(인자));
        Scanner sc = new Scanner(System.in);
        System.out.print("문자 입력 : "); // 웰컴문구
        String str1 = sc.nextLine();
        System.out.println("str1 = " + str1);

        System.out.print("문자 입력 : "); // 웰컴문구
        String str2 = sc.next();
        System.out.println("str2 = " + str2);

        // java.util.InputMismatchException
        // next 함수에 저장된 버퍼 메모리가 정리되지 않아서 생기는 오류
        // 해결방법 : nextLine() 함수를 한번 실행
        sc.nextLine();

        System.out.print("정수 입력 : "); // 웰컴문구
        int num1 = sc.nextInt();
        System.out.println("num1 = " + num1);

        System.out.print("실수 입력 : "); // 웰컴문구
        double num2 = sc.nextDouble();
        System.out.println("num2 = " + num2);

        // Scanner객체가 사용하는 리소스를 정리함
        sc.close();
    }
}
