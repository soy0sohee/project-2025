// CTRL + / = 한줄 주석
// /**/ = 블럭 주석

// 자바 소스 파일 확장자 = .java
// 자바는 클래스에서 시작해서 클래스로 끝

// public : 접근제한자를 모두에게 공개 / 다른 파일이나 폴더에서 접근 가능하도록 공개
// class : 클래스(폴더) 선언 예약어
// ex01 : 사용자가 지정한 클래스명
// {} : block 구분자 / 클래스, 함수, 배열 등에 사용
// static : 정적 변수, 함수에 사용
//        : 프로그램 실행 시 고정된 주소값을 사용값으로 사용
//        : 시작함수(main)나 전역함수, 유틸리티 함수 등에 사용
// void : 함수의 반환값이 없음
// String[] : 문자열 배열
// args : 매개변수 이름
// "Hello Java!" : 문자열 / 쌍따옴표 = 문자열, 단따옴표 = 문자 한 자
// ; : 실행문 뒤에 세미콜론은 필수

public class ex01 {
    // Entry Point : 프로그램 시작점
    public static void main(String[] args) {
        // 콘솔에 문자열을 출력하는 함수
        // System클래스(객체).out객체.println함수
        System.out.println("Hello Java!");
    }
}

