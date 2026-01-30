public class ex07 {
    public static void main(String[] args) {
        // 산술연산자 : + - * / %
        int i = 10;
        int j = 3;
        System.out.println("더하기 : " + (i + j));
        System.out.println("빼기 : " + (i - j));
        System.out.println("곱하기 : " + (i * j));
        System.out.println("나누기 : " + (i / j)); // 정수 몫 = 갯수로 나눔
        System.out.println("나누기 : " + (i / (float)j)); // 실수 몫 = 정비율로 나눔
        System.out.println("나머지 : " + (i % j));

        // 소숫점 없애기
        // 1. Math 함수
        float k = 3.14f;
        System.out.println("소숫점 없애기 : " + Math.floor(k)); // 소수점 내림
        System.out.println("소숫점 없애기 : " + Math.round(k)); // 소수점 반올림
        System.out.println("소숫점 없애기 : " + Math.round(Math.floor(k))); // 소수점 날림
        // 2. (int) 형 변환 연산자
        System.out.println("소숫점 없애기 : " + (int)k);

        // 연습문제
        int n = 123;
        //1. 일의 자릿수 3을 출력하시오.

        //2. 십의 자릿수 2를 출력하시오.

        //3. 백의 자릿수 1을 출력하시오.

        //4. 소숫점 첫째자리를 출력하시오.
        //출력예) 5
        double d = 3.567;

        //5. 소숫점 첫째자리에서 반올림하여 출력하시오.
        //출력예) 4.0

        // 함수 없이 반올림
        // 1. 0.5 더하기
        // 2. (int) 형 변환
        // ex) 3.49 => 3.99 => 3
        //     3.5 => 4.0 => 4
        //     3.99 => 4.49 => 4

        //6. 소숫점 둘째자리에서 반올림하여 출력하시오.
        //출력예) 3.6
    }
}
