public class ex06 {
    public static void main(String[] args) {
        // 연산자의 종류
        // 단항연산자 : ++ == !(논리반전) 형변환
        int i = 10;
        i++;
        System.out.println("++ : " + i);

        i--;
        System.out.println("-- : " + i);

        System.out.println("! : " + !true);

        System.out.println("! : " + !false);

        int j = 20;
        short s = (short)j;
        System.out.println("형 변환 : " + s);
    }
}
