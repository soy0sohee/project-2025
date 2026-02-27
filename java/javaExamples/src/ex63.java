import java.util.Optional;

public class ex63 {
    public static void main(String[] args) {
        // Opational 클래스
        // : null값 체크를 위해 사용
        // : NullPointerException 발생 빈도를 줄이고자 만듦

        String nullValue = "abc"; // 안전하지 않은 코드
        System.out.println(nullValue.toLowerCase());

        // 기존의 null 체크 방법
        if (nullValue != null) {
            System.out.println(nullValue.toLowerCase());
        }

        // Null일 수도 있는 값(객체)로 Opational 객체를 생성
        Optional optional1 = Optional.ofNullable(nullValue);
        // Null 인지(true) 아닌지(false)
        System.out.println(optional1.isPresent());
        // 값이 없는지(true) 있는지(false)
        System.out.println(optional1.isEmpty());

        if (optional1.isPresent()) {
            System.out.println(nullValue.toLowerCase());
        }

        String notNull = "123";
        // of 함수 : 반드시 null이 아닌 값으로만 초기화 가능
        Optional optional2 = Optional.of(notNull);
        // ofElse 함수 : null일 경우 대체 값을 설정
        String optional3 = Optional.of(notNull)
                .orElse("null입니다");

        // map()
        String str = Optional.ofNullable("abc")
                .map(String::toUpperCase)
                .orElse("대문자로 변환 실패");
        System.out.println(str);

        // filter() : 람다식이 true = 값 반환, false = ofElse반환
        String num = Optional.ofNullable("123")
                .filter((val)->{return val.contains("12");})
                .orElse("12값이 없음");
        System.out.println(num);
    }
}