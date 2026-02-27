import java.util.ArrayList;
import java.util.Collections;

public class ex50 {
    public static void main(String[] args) {
        // 데이터 구조 : 데이터를 담는 그릇
        // 1. 변수 - 단 하나의 값(기본형 타입 + String)
        // 2. 배열 - 인덱스가 있는 연속된 공간
        // 3. 리스트 - 인덱스가 있는 연속된 공간(추가,삭제,교체 간편), 인덱스 순서 O / 중복 O
        // 5. 세트 - 집합구조의 데이터 구조, 인덱스 순서 X / 중복 X
        // 4. 맵 - Key-Value 쌍 저장, 인덱스 순서 X / Key 중복 X, Value 중복 O
        // 6. 스택/큐 - Stack/Que 데이터 구조, FILO(First Input Last Out)/FIFO(First Input First Out)

        // 컬렉션 -> 변수와 함수의 모음, 값만 저장
        //        -> List, Set은 컬렉션 자식
        //        -> Map은 컬렉션 자식 아님

        // 컬렉션 프레임워크 : List, Map, Set
        // 배열의 단점을 보완하는 자료구조 모음

        // 1. List(리스트)
        // ArrayList(배열기반) : 순차적으로 데이터 나열, 검색이 빠름
        // LinkedList(열결기반) : 요소들이 앞/뒤 주소로 연결, 삽입/삭제가 빠름
        //       -> 제네릭(타입)
        ArrayList<String> fruits = new ArrayList<String>();
        System.out.println(fruits); // [] 빈배열

        // add() : 맨 뒤에 요소 추가
        fruits.add("수박");
        System.out.println(fruits); // [수박]
        fruits.add("망고");
        fruits.add("딸기");
        System.out.println(fruits);

        // add(인데스, 엘리먼트) : 맨 뒤에 요소 추가
        fruits.add(0, "레몬");
        System.out.println(fruits);
        fruits.add(4, "레몬");
        System.out.println(fruits);

        // remove() : 인덱스 요소 삭제
        fruits.remove(0);
        System.out.println(fruits);

        // 요소 변경/치환
        fruits.set(1,"복숭아");
        System.out.println(fruits);

        // 인덱스 호출
        System.out.println(fruits.get(0));

        // size() : 실제 들어있는 데이터 개수
        System.out.println(fruits.size());
        // 오름차순
        Collections.sort(fruits);
        System.out.println(fruits);
        // 오름차순
        Collections.sort(fruits, Collections.reverseOrder());
        System.out.println(fruits);
        //전체 삭제
        fruits.clear();
        System.out.println(fruits);

    }
}
