public class ex02 {
    public static void main(String[] args) {
        // 출력문
        System.out.println("화면출력");
        System.out.println("화면"+"출력"); // 문자열 합
        System.out.println("10 "+10); // 문자열+숫자 합
        System.out.println(10+" 10");
        System.out.println(10+10); // 숫자 합

        // println : 출력 후 줄바꿈
        System.out.println("한줄 출력 후 줄바꿈");
        // print : 줄바꿈 없음
        System.out.print("한줄 출력 후 줄바꿈 없음");
        System.out.print(" -> 연결되어 보임");
        // 한줄 줄바꿈 용도
        System.out.println();
        // printf : 형식화된 출력문
        // %d, %o, %x, %e, %f = 값을 어떤 형태로 출력할지 정해주는 표시
        // \n : 줄바꿈
        System.out.printf("%d\n", 30); // %d = decimal, 10진 정수
        System.out.printf("%o\n", 30); // %o = octal, 8진 정수
        System.out.printf("%x\n", 30); // %x = hexa, 16진 정수
        System.out.printf("%e\n", 300.0); // %e = exporental, 지수형 표현

        // 자릿수 맞추기
        System.out.printf("%5d\n", 123); // 최소 자리수(남는 자리수를 공백으로 맞춤)
        System.out.printf("%5d\n", 123456);
        System.out.printf("%05d\n", 123); // 최소 자리수(남는 자리수를 0으로 맞춤)
        // %f : float, 실수 / f를 생략하면 double
        System.out.printf("%05.2f\n", 123.45); // 수수점 두번째 자리
    }
}
