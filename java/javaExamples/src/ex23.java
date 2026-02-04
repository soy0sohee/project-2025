public class ex23 {
    // 메서드는 메서드 밖에서 선언
    static void echo(){ // 야호
        System.out.println("echo");
    }
    static void echo(String str){ // 매개변수의 갯수 또는 타입이 다름
        System.out.println("echo"+str);
    }
    static void echo(int i){
        System.out.println("echo"+i);
    }
    static void echo(int i, int j){
        System.out.println("echo"+i+","+j);
    }

    public static void main(String[] args) {
        echo();
        echo("야호~");
        echo(10);
        echo(10, 20);

        // 메서드 오버로딩(Over Loading)
        // : 과정(적정한 부하 이상으로 짐을 담는 것
        // : 매개변수의 타입과 갯수를 다르게 하여 함수의 기능을 확장한 것 - ex) println
        // 사용하는 이유 : 같은 이름의 함수를 여러번 쓸 수 있음
        // 사용 예)
        System.out.println(10);
        System.out.println("문자열");


        // 메서드 오버라이딩(Over Riding)
        // : 상속 관계에서 자식 클래스의 메서드가 부모 클래스의 메서드를 재정의하는 것
    }
}
