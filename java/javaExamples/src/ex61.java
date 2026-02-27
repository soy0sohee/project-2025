import java.util.function.BiFunction;

public class ex61 {
    public static void main(String[] args) {
        // 람다(식)
        // JDK 1.8 지원
        // 매개변수 2개까지 지원, apply 메서드로 호출
        // BiFunction<첫번째변수타입, 두번째변수타입, 반환값타입> 변수 = (첫번째변수, 두번째변수) -> {return 조건식}
        BiFunction<Integer, Integer, Integer> sum = (x,y) -> {return x+y;};
        System.out.println(sum.apply(10,20));
    }
}
