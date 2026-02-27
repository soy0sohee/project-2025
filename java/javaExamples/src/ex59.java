import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ex59 {
    public static void main(String[] args) {
        // Hash
        // : 데이터의 중복을 피해서 특정위치에 배치하는 알고리즘
        // : 예) 도서관의 책에 붙은 태그 => 13마1234(13구간 마열의 1234번째)

        // set : 중복된 요소를 허용하지 않은 자료구조
        Set<String> set = new HashSet<>();
        set.add("홍길동");
        set.add("이순신");
        set.add("변사또");
        System.out.println(set);

        // 중복을 허용하지 않기에, 같은 데이터를 넣으면 추가 안됨
        boolean isAdded = set.add("홍길동");
        System.out.println(isAdded);
        System.out.println(set);

        // 향상된 for문 전체 순회
        for (String name : set) {
            System.out.println(name);
        }
        // Iterator 전체 순회
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 집합 연산 = 교집합, 합집합, 차집합
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();

        setA.add(10);
        setA.add(20);
        setA.add(30);

        setB.add(30);
        setB.add(40);
        setB.add(50);

        // 원본 복사본
        // 배열, 리스트, 맵, 세트에서 복사할 때 = 주소값 복사
        Set<Integer> setC = setA;
        System.out.println(setC);
        // 내용까지 모두 복사
        Set<Integer> setD = new HashSet<>(setA);
        System.out.println(setD);

        // 교집합
        // setA.retainAll(setB); // A기준 B와 같은것만 남기고 삭제, 원본 손상
        // System.out.println(setA);

        System.out.println(setC); // 주소값을 복사한 것으로 setA이 변경되면 같이 원본 손상됨
        System.out.println(setD); // 다른 주소값에 원본 내용을 복사한 것으로 setA를 변경해도 원본 살아있음

        // 합집합
        // setA.addAll(setB); // A 기준 B를 합침
        // System.out.println(setA);

        // 차집합
        setA.removeAll(setB); // A 기준 B와 같은것 뺴고 배열 생성
        System.out.println(setA);
    }
}
