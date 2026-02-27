public class ex41 {
    public static void main(String[] args) {
        // String 관련 클래스
        // StringBuffer : 문자열을 유연하게 제어 / 멀티스레드 / 데이터동기화지원
        // StringBuilder : 대량의 문자열을 처리할 때 속도향상 / 단일스레드 / 데이터동기화미지원
        StringBuffer sb = new StringBuffer("abc");
        System.out.println(sb);
        sb.append("def");
        System.out.println(sb);
        sb.insert(3,"123");
        System.out.println(sb);

        StringBuilder builder = new StringBuilder(sb);
        System.out.println(sb);
        sb.append("def");
        System.out.println(sb);
        sb.insert(3,"123");
        System.out.println(sb);

    }
}
