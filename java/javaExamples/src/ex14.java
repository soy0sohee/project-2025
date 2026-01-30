public class ex14 {
    public static void main(String[] args) {
        // 2. while
        // 초기화문;
        // while (조건식(절)) {
        //  실행문;
        //  증감문;
        // }
        int i = 0;
        while (i < 5) {
            System.out.println("i = " + i);
            i++;
        }

        // 3. do-while : 한번 수행 후 조건 비교
        // 초기화문;
        // do {
        //  실행문;
        //  증감문;
        // } while (조건식(절));
        int j = 0;
        do {
            System.out.println("j = " + j);
            j++;
        } while (j < 5);
    }
}
