public class ex16 {
    public static void main(String[] args) {
        // 2차 배열 선언
        // 1.
        int[][] arrNum1 = {{10,20,30},{40,50,60}};
        // 2. 2행 3열
        int[][] arrNum2 = new int[2][3];
        // 3.
        int[][] arrNum3 = new int[][]{{10,20,30},{40,50,60}};
        // 4. 2행 열 없음
        int[][] arrNum4 = new int[2][];
        // 열 초기화
        arrNum4[0] = new int[3];
        arrNum4[1] = new int[3];

        // 행의 길이
        System.out.println(arrNum4.length);
        // 열의 길이
        System.out.println(arrNum4[0].length);
        System.out.println(arrNum4[1].length);

        // 2차배열의 순회
        for (int i = 0; i < arrNum1.length; i++) {
            for (int j = 0; j < arrNum1[i].length; j++) {
                System.out.println(arrNum1[i][j]);
            }
        }
        // for 콜론문
        // for (자료형 변수 : 배열 또는 컬렉션) {
        //  실행문;
        // }
        for (int[] nums : arrNum1) { // arrNum1에서 행을 한줄씩 꺼냄 -> nums에 저장
            for (int num : nums) { // nums 배열 안의 각 숫자를 하나씩 꺼냄
                System.out.println(num);
            }
        }
    }
}
