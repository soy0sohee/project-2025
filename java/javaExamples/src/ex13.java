public class ex13 {
    public static void main(String[] args) {
        // 일차 반복문 : 1차 배열 접근
        // 이중 반복문 : 2차 배열 접근
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
//                System.out.println(i + "," + j);
            } // for
        } // for

        // 정수형 2차배열
        int[][] nums2D = {{1, 2}, {3, 4}};
        // 전체 순회
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(nums2D[i][j]);
            } // for
        } // for

        // 별찍기
        // *****
        // *****
        // *****
        // *****
        // *****
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("*");
            } // for
            System.out.println();
        } // for
        System.out.println();

        // 연습문제
        // 1.
        //     *
        //    **
        //   ***
        //  ****
        // *****
        int count5 = 5;
        for (int i = 0; i < 5; i++) {
            count5--;
            for (int j = 0; j < 5; j++) {
                if (j >= count5) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                } // if
            } // for
            System.out.println();
        } // for
        System.out.println();

        // 2.
        // *******
        // *    **
        // *   * *
        // *  *  *
        // * *   *
        // **    *
        // *******
        int count7 = 7;
        for (int i = 0; i < 7; i++) {
            count7--;
            for (int j = 0; j < 7; j++) {
                if(i == 0 || i == 6 || j == 0 || j == 6){
                    System.out.print("*");
                } else {
                    if(j == count7){
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    } // else - if
                } // if
            } // for
            System.out.println();
        } // for
    } // main
} // class
