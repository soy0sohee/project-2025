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
        // 1. Math.random() 0.0~0.999...
        int rand1 = (int)(Math.random()*6);
        System.out.println("rand1 = " + rand1);

        // 2. Random 클래스 import
        //    -> rand.nextInt(6) 0~6
        Random rand2 = new Random();
        int num = rand2.nextInt(6);
        System.out.println("rand2 = " + num);

        //

    } // main
} // class






























