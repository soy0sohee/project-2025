import java.util.Random;

public class ex10 {
    public static void main(String[] args) {
        // 조건문 = JS 문법과 거의 유사
        // 실행문이 한 줄 일때 중괄호 생략 가능
        if (10 < 20) System.out.println("10은 20보다 작음");

        // 1. if
        if (10 < 20) {
            System.out.println("10은 20보다 작음");
        }

        // 2. if - else
        if (10 < 20) {
            System.out.println("10 < 20 t");
        } else {
            System.out.println("10 < 20 f");
        }

        // 3. if - else if
        int score = 90;
        if (score >= 90) {
            System.out.println("90 이상");
        } else if (score >= 80) {
            System.out.println("80 이상");
        } else {
            System.out.println("80 미만");
        }

        // 4. 중첩 if
        if (true) {  // 조건1
            if (false) {  // 조건2
                System.out.println("조건1 = 참, 조건2 = 참");  // 조건1 = 참, 조건2 = 참
            } else if (true) {  // 조건3
                System.out.println("조건1 = 참, 조건2 = 거짓, 조건3 = 참");  // 조건1 = 참, 조건2 = 거짓, 조건3 = 참
            }
        }

        // 랜덤수 발생
        // 1. Math.random() // 0.0~0.999...
        int rand1 = (int) (Math.random() * 6); // 0.0~5.999...
        System.out.println("rand1 = " + rand1);

        // 2. Random 클래스 import
        //    -> rand.nextInt(6) = 0~6미만
        Random rand2 = new Random();
        int num = rand2.nextInt(6);
        System.out.println("rand2 = " + num);

        // 연습문제
        // 1. 철수와 영희가 주사위 놀이를 하고 있다.
        //    주사위 2개를 던져서,
        //    두개 다 짝수가 나오면 철수 승!
        //    두개 다 홀수가 나오면, 영희 승!
        //    그외의 경우는 무승부! 이다.
        //    게임의 결과를 출력하시오.
        int num1 = (int) (Math.random() * 6) + 1;
        int num2 = (int) (Math.random() * 6) + 1;
        System.out.println("주사위1 수 : " + num1);
        System.out.println("주사위2 수 : " + num2);

        if (num1 % 2 == 0 && num2 % 2 == 0) {
            System.out.println("철수 승!");
        } else if (num1 % 2 == 1 && num2 % 2 == 1) {
            System.out.println("영희 승!");
        } else {
            System.out.println("무승부!");
        }

        //2. 철수와 영희을 주사위게임을 하고 있다.
        //   주사위 2개를 철수가 던지고,
        //   주사위 2개를 영희도 던진다.
        //   게임룰 : 첫번째 주사위는 십의 자릿수로하고,
        //            두번째 주사위는 일의 자릿수로 해서,
        //   더 높은 점수를 가진 사람이 승리한다.
        //   출력값 예시 :
        //        철수 주사위1 수 : 1
        //        철수 주사위2 수 : 3
        //        철수의 점수는 13
        //        영희 주사위1 수 : 3
        //        영희 주사위2 수 : 4
        //        영희의 점수는 34
        //        영희 승!
        Random randomNum = new Random();
        int chulNum1 = randomNum.nextInt(6) + 1;
        int chulNum2 = randomNum.nextInt(6) + 1;
        int chulNum = (chulNum1 * 10) + chulNum2;
        System.out.println("철수 주사위1 수 : " + chulNum1);
        System.out.println("철수 주사위2 수 : " + chulNum2);
        System.out.println("철수의 점수는 " + chulNum);

        int youngNum1 = randomNum.nextInt(6) + 1;
        int youngNum2 = randomNum.nextInt(6) + 1;
        int youngNum = (youngNum1 * 10) + youngNum2;
        System.out.println("영희 주사위1 수 : " + youngNum1);
        System.out.println("영희 주사위2 수 : " + youngNum2);
        System.out.println("철수의 점수는 " + youngNum);

        if (chulNum > youngNum) {
            System.out.println("철수 승!");
        } else if (chulNum < youngNum) {
            System.out.println("영희 승!");
        } else if (chulNum == youngNum) {
            System.out.println("무승부!");
        } else {
            System.out.println("오류");
        }

        // 변수, 함수, 클래스 이름 짓기
        // 자바 = 카멜(Camel) : myStudentEventPoint
        // 자바스크립,파이썬 = 스네이크(Snake) : my_student_event_point
    } // main
} // class






























