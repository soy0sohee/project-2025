public class ex44 {
    static void myFunc() throws Exception{
        System.out.println(10 / 0);
    }
    public static void main(String[] args) {
        // 2. throw문
        // : 자신을 호출한 메서드에 예외 처리를 떠넘기는 것
        // : 예외처리가 귀찮음, 코드가 간결해 짐
        try {
            myFunc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("수정하기 귀차나");
        }

        int num = 1;
        System.out.println(num);
    }
}
