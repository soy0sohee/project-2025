// 접근제한자
// : 클래스, 함수, 변수 앞에 위치하며 접근을 제한할 때 사용

// public : 같은 폴더(패키지)에서, 다른 폴더의 클래스에 접근 가능
// protected : 같은 폴더 + 상속관계 클래스에서 접근 가능
// default : 같은 폴더, 생략가능
// private : 같은 클래스 안에서 접근 가능(캡슐화, 은닉)
//         : 다른 클래스에서는 getter/setter함수를 통해서만 접근 가능
//         : 변수나 함수를 최대한 노출하지 않는 것(값 관리 - 접근제어)
class Hong {
    String name = "홍길동"; // default : 같은 폴더, 자기 클래스 접근
    public int age = 20; // public : 다른 폴더, 상속관계, 같은 폴더, 자기 클래스 접근
    protected int korScore = 80; // protected : 상속관계, 같은 폴더, 자기 클래스 접근
    private int engScore = 90; // private : 자기 클래스에만 접근

    // private의 경우 getter/setter 함수 제공
    public int getEngScore() { // getter : 값 조회 전용
        return this.engScore;
    }
    public void setEngScore(int engScore) { // setter : 값 변경 전용, type = void
        this.engScore = engScore;
    }
}

public class ex21 {
    public static void main(String[] args) {
        Hong hong = new Hong();
        System.out.println(hong.name);
        System.out.println(hong.age);
        System.out.println(hong.korScore);
        // System.out.println(hong.engScore);
         hong.setEngScore(100);
        System.out.println(hong.getEngScore());
    }
}
