public class ex43 {
    public static void main(String[] args) {
        // 예외처리(Exception Handing)
        // : 예외 - 에러, 예상치 못한 오류
        // : 실행(런타임) 시에 처리 가능한 문법
        
        // 1. try - catch - finally 문
        // : 예외를 직접 처리

        // try {
        //  예외가 발생할 만한 실행문
        // }
        // catch (예외클래스 객체) {
        //  예외 발생시 처리하는 실행문(에러내용 출력)
        // }
        // finally {
        //  예외가 발생하든지 안하든지 무조건 수행
        //  수행하던 코드(자원-메모리)를 정리하는 코드
        // }

        // 대표 오류
        // null Exception(널 처리 오류)
        String name = null;
        System.out.println(name);
        // NullPointerException :
        // Cannot invoke(함수실행) "String.toLowerCase()" because "name" is null
        // System.out.println(name.toLowerCase());
        try {
            System.out.println(name.toLowerCase());
        }
        catch (Exception e) { // Exception 클래스를 상속받음
            System.out.println("1. "+e.getMessage()); // 예외 메시지 출력
            e.printStackTrace(); // 예외 발생 경로 출력
        }
        finally {
            System.out.println("정리하는 코드");
            // 예) scan.close();
        }

        try {
            // 배열 인덱싱 예외
            // ArrayIndexOutOfBoundsException : Index 3 out of bounds for length 3
            int[] nums = {10, 20, 30};
            System.out.println(nums[3]);

            // 0으로 나누기
            // ArithmeticException
            System.out.println(10 / 0);
        }
        catch (ArrayIndexOutOfBoundsException e) { // Exception 클래스를 상속받음
            System.out.println("3. "+e.getMessage());
        }
        finally {
            System.out.println("정리하는 코드");
            // 예) scan.close();
        }

        System.out.println("Hi~"); // 예외 실행문으로 오류가 발생해도 폴더가 죽지 않음

    }
}
