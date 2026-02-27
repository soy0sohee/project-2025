// 일반적인 Exception 외 추가적인 기능 커스텀
class MyException extends Exception {
    String message = "";
    public void printMessage(){
        System.out.println(this.message);
    }
}
public class ex45 {
    static void myFunc() throws MyException {
        MyException e = new MyException();
        e.message = "사용자 정의 예외";
        throw e; // 예외 강제 발생
    }
    public static void main(String[] args) {
        // 사용자 정의 예외
        try {
            myFunc();
        } catch (Exception e) {
            MyException me = (MyException)e;
            me.printMessage();
        }
    }
}


