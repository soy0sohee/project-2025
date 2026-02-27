public class ex42 {
    public static void main(String[] args) {
        // 래퍼(wrapper) 클래스
        // 1. 기본 자료형 8개를 감싸서 클래스로 만든것
        // 2. 클래스로 만들어서 기능 확장 및 다형성 확보
        // 기본 자료형 : int long short byte float double char boolean
        // 래퍼 클래스 : Integer Long Short Byte Float Double Character Boolean
        // 문자열 : String

        // 숫자클래스 : Number = int long short byte float duble

        // 래퍼 클래스 선언
        Integer intV1 = new Integer(10); // JDK9부터 중단
        Integer intV2 = 10;
        System.out.println(intV2.toString());
        Boolean bool = true;
        System.out.println(bool.toString());

        Number num1 = 1;
        Number num2 = 1d;
        Number num3 = 1f;
        System.out.println(num1.shortValue() + 5);

    }
}
