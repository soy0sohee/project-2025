public class ex04 {
    public static void main(String[] args) {
        // 형 변환 type casting
        // 형 변환 공식
        // 1. 작은 정수형 -> 큰 정수형 = 문제없음
        // 2. 큰 정수형 -> 작은 정수형 = error
        // 3. 정수형 -> 실수형 = 문제없음
        // 4. 실수형 -> 정수형 = 값 잘림, 소숫점 날라감
        // 즉, 표현범위를 벗어나면 문제가 생김
        
        // 자동(암묵적) 형변환 : 대입(산술)연산자를 통해 자동으로 형변환
        // 수동(명시적) 형변환 : 형변환 연산자를 통해 형변환

        // 자동 형변환
        // 1. 같은 타입끼리
        //  int * int => int
        //  long * long => long
        int a = 10 * 20; // int(10) * int(20) = int(200) = int a(200)
        System.out.println("같은 타입 형변환 : " + a);

        // 2. 다른 타입끼리 => 다 큰 타입으로 변환
        //  int * long => long
        //  long * float => float
        int b1 = 10;
        long b2 = 20L;
        System.out.println("다른 타입 형변환 : " + b1 * b2);

        // 수동 형변환
        // 큰 타입에서 작은 타입으로 값 대입할 때
        // int e = 20L; // 값이 잘리므로 대입 불가
        int e = (int)20L; // 8바이트 -> 4바이트
        // int g = 3.14f; // 값이 잘리므로 대입 불가
        int g = (int)3.14f; // 강제 형변환 3.14 -> 3

        // 자바스크립트 typeof연산자를 대체하는 자바 연산자는 없음
        // 자바에서 데이터 타입 확인하는 방법
        int i = 10;
        float j = 1.24f;
        System.out.println(((Object)i).getClass().getSimpleName());
        System.out.println(((Object)j).getClass().getSimpleName());
    }
}
