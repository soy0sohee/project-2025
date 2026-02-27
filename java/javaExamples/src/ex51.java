import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ex51 {
    public static void main(String[] args) {
        // ArrayList 사용예
        // ArrayList = 가변용량 -> 넣은만큼 들어감
        // ArrayList 선언방식
        ArrayList<Integer> nums1 = new ArrayList<Integer>(); // 타입명시
        ArrayList<Integer> nums2 = new ArrayList<>(); // 타입생략
        ArrayList<Integer> nums3 = new ArrayList<>(10); // 내부 배열 길이 : 10
        ArrayList<Integer> nums4 = new ArrayList<>(
                Arrays.asList(10,20,30,40,50) // ArrayList 안에 Arrays 클래스로 데이터 삽입
        );
        System.out.println(nums4);
        ArrayList<Integer> nums5 = new ArrayList<>(nums4); // 다른 ArrayList로 초기화 선언
        System.out.println(nums5);

        // 전체 리스트 순회
        // for문
        for(int i = 0; i < nums5.size(); i++){
            System.out.println(nums5.get(i));
        }
        // 향상된 for문
        for(Integer num : nums5){
            System.out.println(num);
        }

        // 이터레이터(Iterator) : 열거자
        // 컬렉션의 순차적인 처리를 도와주는 클래스
        // hasNext() : 다음 요소를 가지고 있는지 T/F 반환
        // next() : 다음 요소 반환
        Iterator<Integer> it = nums5.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        // contains(요소) : 해당 요소를 가지고있는지 T/F 반환
        if(nums5.contains(30)){
            System.out.println("30포함");
        }
        // indexOf(요소) : 특정요소의 인덱스 가져오기
        System.out.println(nums5.indexOf(40));
    }
}
