import java.util.Arrays;
import java.util.Collections;

public class ex15 {
    public static void main(String[] args) {
        // ES6 => 배열 + 리스트 하나로 통합되어 있음
        // 배열 : 데이터가 순차적으로 놓여있는 자료구조
        // 리스트 : 배열 + 중간에 추가/삭제가 가능

        // 자바 => 배열과 리스트 분리되어 있음

        // 정수형 1차 배열
        // 자바스크립트 => let arrNums = [10, 20, 30];
        int[] arrNums = {10, 20, 30}; // 재할당 불가 배열 생성
        System.out.println("arrNums[0] = " + arrNums[0]);
        System.out.println("arrNums[1] = " + arrNums[1]);
        System.out.println("arrNums[2] = " + arrNums[2]);

        int arrNums2[] = {10, 20, 30}; // 추천하지 않음
        System.out.println("arrNums2[0] = " + arrNums2[0]);
        System.out.println("arrNums2[1] = " + arrNums2[1]);
        System.out.println("arrNums2[2] = " + arrNums2[2]);

        int[] arrNums3 = new int[3]; // 배열의 길이 생성, 기본값 = 0
        System.out.println("arrNums3[0] = " + arrNums3[0]);

        int[] arrNums4 = new int[]{10, 20, 30}; // 재할당 가능한 배열 생성
        System.out.println("arrNums4[0] = " + arrNums4[0]);
        System.out.println("arrNums4[1] = " + arrNums4[1]);
        System.out.println("arrNums4[2] = " + arrNums4[2]);

        // 안되는 경우
        // int[] = a;
        // a = {10, 20, 30};

        // 배열의 순환
        for (int i = 0; i < arrNums.length; i++) {
            System.out.println("i = " + i);
        }

        // for-:문 : 값 순환
        // 자바스크립트 : for-of문
        for (int num : arrNums) {
            System.out.println(num);
        }

        // for-each문

        // 배열의 정렬 - 오름차순
        int[] nums = {10, 30, 20, 50, 40};
        Arrays.sort(nums);
        System.out.println("Arrays.toString(nums) = " + Arrays.toString(nums));

        // 배열의 정렬 - 내림차순
        Integer[] nums2 = {10, 30, 20, 50, 40};
        Arrays.sort(nums2, Collections.reverseOrder());
        System.out.println("Arrays.toString(nums2) = " + Arrays.toString(nums2));

        // Integer = 정수형 Wrapper 클래스
        // 클래스로서 int에 없는 기능을 확장한 클래스(객체)
        // int를 클래스로 만든것 -> 다형성을 이용한 데이터 이동이 편지해짐
    }
}
