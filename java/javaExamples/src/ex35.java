class People {
    void think() {
        System.out.println("사람 생각");
    }
}
class Man extends People {
    @Override
    void think() {
        System.out.println("남자 생각");
    }void shave() {
        System.out.println("면도");
    }
}
class Woman extends People {
    @Override
    void think() {
        System.out.println("여자 생각");
    }
    void makeup() {
        System.out.println("메이크업");
    }
}
public class ex35 {
    public static void main(String[] args) {
        People p1 = new Man();
        p1.think();
        // instanceof 연산자
        // : 객체타입을 확인하는 연산자
        // : 특정 클래스의 인스턴스인지 or 그 클래스를 상속받은 자식 클래스인지 확인하여 t/f로 반환하는 연산자
        if(p1 instanceof Man){
            System.out.println("Man객체");
        } else if (p1 instanceof Woman) {
            System.out.println("Woman객체");
        }

        People p2 = new Woman();
        p2.think();
        // :다운캐스팅 하기 전에 안전 검사용
        if(p1 instanceof Man){
            Man man = (Man)p1;
            System.out.println("Man객체");
            man.think();
        } else {
            System.out.println("객체 다름");
        }
        Man man = (Man)p1;
        man.think();
        man.shave();

        Woman woman = (Woman)p2;
        woman.think();
        woman.makeup();

        myFunc(new People());
        myFunc(new Man());
        myFunc(new Woman());
    }
    static void myFunc(People p){
        // 여러 자식 클래스 분기 처리할 때 사용
        if(p instanceof Man){
            Man M = (Man)p;
            M.shave();
        } else if(p instanceof Woman){
            Woman W = (Woman)p;
            W.makeup();
        } else {
            p.think();
        }
    }
}
