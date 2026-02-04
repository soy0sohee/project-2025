// 생성자 함수(Constructor)
// : Class가 생성될 때(new) 자동으로 호출되는 메서드
// : 메서드 이름과 클래스 이름 동일
// : 필드값을 초기화 하는 용도로 사용

class Book {
    // 속성(필드)
    int price = 1000;

    // 생성자 함수 패턴
    // public : 어디서나 접근 가능
    // public 반환타입x 클래스이름() {}
    public Book(int price) {
        this.price = price;
        System.out.println(this.price);
        System.out.println("생성자함수 자동호출");
    }

    // 행동
    void read(){
        System.out.println("읽음");
    }
}

public class ex26 {
    public static void main(String[] args) {
        Book book = new Book(2000);
        System.out.println(book.price);
    }
}
