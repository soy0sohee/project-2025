import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ex62 {
    public static void main(String[] args) {
        // Stream 계열의 함수
        // : 알고리즘(코딩테스트)시에 복잡하지만 강력한 기능
        // : JS 배열.map() reduce() filter()

        // filter함수 : 조건 필터링
        // 10보다 큰 숫자만 필터
        List<Integer> nums = Arrays.asList(1,15,8,20,5,30);
        List<Integer> result = nums.stream()
                .filter((n) -> {return n > 10;})
                .collect(Collectors.toList());
        System.out.println(result);

        // map함수 : 데이터 변형
        // 대문자로 변환
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        List<String> upperWords = words.stream()
                .map(String::toUpperCase) // :: 메서드 참조 뜻
                .collect(Collectors.toList());
        System.out.println(upperWords);

        // reduce : 결과를 하나의 값으로
        // 모든 숫자를 합으로
        List<Integer> vals = Arrays.asList(1,2,3,4,5);
        int sum = vals.stream()
                .reduce(0, (acc, cur) -> {return acc+cur;}); // .reduce(초기값, (acc, cur) -> {return 조건식;})
        System.out.println(sum);

        // sorted : 정렬
        // 가나다순 정렬
        List<String> names = Arrays.asList("이순신","강감찬","을지문덕");
        List<String> sorted = names.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sorted);

        // limmit : 개수 제한
        List<Integer> nums2 = Arrays.asList(1,2,3,4,5);
        List<Integer> firstThree = nums2.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(firstThree);

        // anyMatch : 조건에 만족하는 요소를 boolean값으로
        List<String> tect = Arrays.asList("Java","Sprint","Python");
        boolean hasPython = tect.stream()
                .anyMatch((s)->{return s.equals("Pyton");});
        System.out.println(hasPython);

        // collect(groupingBy) : 그룹화
        // 첫글자 기준으로 그룹 묶기
        List<String> items = Arrays.asList("Apple","Ant","Banana","Box","Car");
        Map<Character,List<String>> groupByFirstCahr = items.stream()
                .collect(Collectors.groupingBy((s)->{return s.charAt(0);}));
        System.out.println(groupByFirstCahr);
    }
}
