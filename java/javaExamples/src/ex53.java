// 제네릭(Generic)
// : 클래스나 메서드에서 사용할 타입을 미리 정하지 않고, 사용할 때 타입을 결정하는 문법

// : 자바 = 정적타입 언어 -> 타입 호환성이 엄격함
// : 타입에 따른 데이터 전송을 편하게 하기 위해 가변적인 타입 선언을 할 수 있도록 함
//   -> ** 다형성 상속과는 관련없음 **
// : JDK 1.5부터 지원

// 다형성을 이용한 유연한 타입 지원
// 최상위 클래스 Object를 이용하여 모든 객체를 담을 수 있음
// 단점, 다운캐스팅 필요
class Keyboard1 {
    private Object object;
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
}

// 제네릭을 이용한 유연한 타입 지원
class Keyboard2<type> {
    private type object;
    public type getObject() {
        return object;
    }
    public void setObject(type object) {
        this.object = object;
    }
}

public class ex53 {
    public static void main(String[] args) {
        Keyboard1 kb1 = new Keyboard1();
        kb1.setObject("키보드1"); // (String -> Object)업캐스팅 자동전환
        String str1 = (String)kb1.getObject(); // (Object -> String)다운캐스팅 강제전환
        System.out.println(str1);

        Keyboard2<Integer> kb2 = new Keyboard2<>();
        kb2.setObject(10);
        Integer int1 = kb2.getObject();
        System.out.println(int1);

        Keyboard2<String> kb3= new Keyboard2<>();
        kb3.setObject("문자열");
        String str2 = kb3.getObject();
        System.out.println(str2);
    }
}
