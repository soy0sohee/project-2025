// 상속관계에서 생성자 함수
class Energy {
    int price = 1000;

    // 기본생성자, 매개변수가 없는 생성자 함수
    public Energy() {
        System.out.println("E-1 " + this.price);
    }

    // 매개변수가 있는 생성자 함수(오버로딩)
    public Energy(int price) {
        this.price = price;
        System.out.println("E-2 " + this.price);
    }
}
class WindEnergy extends Energy {
    int price = 3000;
    public  WindEnergy(){
        System.out.println("WE-1 " + this.price);}
    public  WindEnergy(int price){
        // super : 부모클래스 객체의 주소를 가르키는 예약어
        // super(); // 부모클래스의 기본 생성자 함수를 호출하는 명령어
        // 부모 클래스의 생성자함수를 호출함으로 값을 초기화
        // super(price); // 부모클래스의 매개변수 있는 생성자를 호출하는 명령어

        // this. : 자신의 멤버(변수,함수)에 접근
        // this() : 자신의 생성자함수에 실행
        // super. : 부모의 멤버(변수,함수)에 접근
        // super() : 부모의 생성자함수에 실행

        this.price = price;
        System.out.println("WE-1 " + this.price);
    }
}

public class ex31 {
    public static void main(String[] args) {
        // 기본 생성자함수를 이용한 객체 생성
//        Energy energy1 = new Energy();
        // 매개변수 있는 생성자함수를 이용한 객체 생성
//        Energy energy2 = new Energy(2000);

//        WindEnergy windEnergy1 = new WindEnergy();
//        WindEnergy windEnergy2 = new WindEnergy(4000);

        Energy energy3 = new WindEnergy();
    }
}
