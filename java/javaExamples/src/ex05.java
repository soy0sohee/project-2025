import java.util.Arrays;

public class ex05 {
    public static void main(String[] args) {
        // 문자열 관련 함수들
        String str1 = "Hello Java!";
        String str2 = "안녕하세요! 반값습니다.";

        // 문자열 길이 : 공백포함
        System.out.println("문자열 길이 : " + str1.length());
        System.out.println("문자열 길이 : " + str2.length());

        // 문자 한자 가져오기
        char c1 = str1.charAt(0);
        System.out.println("문자 호출 : " + c1);
        char c2 = str1.charAt(1);
        System.out.println("문자 호출 : " + c2);

        // 문자열의 위치(index) 가져오기
        System.out.println("문자열 index 호출 : " + str1.indexOf("Java"));

        // 마지막 문자열의 위치(index) 가져오기
        System.out.println("문자열 index 호출 : " + str1.lastIndexOf("a"));

        // 대문자로 바꾸기
        String str3 = "Java Study";
        System.out.println("대문자 변환 : " + str3.toUpperCase());

        // 소문자로 바꾸기
        System.out.println("소문자 변환 : " + str3.toLowerCase());

        // 문자열 검색 : 대문자 또는 소문자 변환하여 검색
        System.out.println("문자열 검색 : " + str3.toLowerCase().indexOf("study"));

        // 문자열 치환하기
        System.out.println("문자열 치환 : " + str3.replace("Study","공부"));

        // 문자열 일부 가져오기 : 시작Index , 끝Index - 1
        System.out.println("문자열 호출 : " + str3.substring(0,4));

        // 문자열 분리, 배열 반환
        String[] strArr = "abc/def-ghi".split("/|=| ");
        System.out.println("문자열 배열 : " + Arrays.toString(strArr));

        // 문자열 앞뒤 공백 제거
        System.out.println("문자열 공백제거 : " + " abc ".trim());

        // 문자열 모든 공백 제거
        System.out.println("문자열 공백제거 : " + " a b c ".replaceAll(" ", ""));

        // 문자열 연결
        System.out.println("문자열 연결 : " + "abc".concat("123"));

        // 문자열 포함 여부
        System.out.println("문자열 포함여부 : " + "abc123".contains("123"));

        // 문자열 내용 비교할 때 JAVA 특징
        String str4 = "Java";
        String str5 = "Java";
        System.out.println("문자열 비교 : " + str4.equals(str5));
        // System.out.println("문자열 비교 : " + str4 == str5);

        // 대부분의 언어들이 문자열을 비교할 때 == 을 사용
        // JAVA는 == 으로 문자열 비교 불가
        // 주소값을 가진 정수를 비교하므로 안 됨

        String str6 = "abc";
        String str7 = new String("abc");
        System.out.println("문자열 비교 : " + str6.equals(str7));
        // System.out.println("문자열 비교 : " + str6 == str7);
    }
}
