public class ex11 {
    public static void main(String[] args) {
        // Switch문
        // 개발환경(window/mac)의 JDK버전과 배포환경(리눅스)의 JDK버전이 일치해야함
        // 보통 배포는 1.8버전으로 배포함
        // 도커(컨테이너) 기반의 배포에서는 APP + 개발환경을 하나의 패키지로 배포하므로 배포환경의 영향을 거의 받지 않음
        // 도커(컨테이너) 기반 : MSA, 클라우드
        // 클라우드 : 누구나 서버에 접속해서 동일한 환경으로 서비스를 이용한 것
        //          : 배포 및 서비스도 클라우드를 이용

        // JAVA 8에서 ()안에 들어갈 수 있는 타입
        // 정수형 : byte, short, char, int
        // 문자열 : String
        // 열거형(enum) : enum
        // 불가 = boolean, long, float, double

        int score = 90;
        switch (score) {
            case 90:
                System.out.println("90");
                break;
            case 80:
                System.out.println("80");
                break;
            default:
                System.out.println("80 미만");
                break;
        } // switch

        String fruit = "apple";
        switch (fruit) {
            case "berry":
                System.out.println("berry");
                break;
            case "apple":
                System.out.println("apple");
                break;
        } // switch

        int month = 3;
        switch (month) {
            case 3,4,5: // java14부터 가능
                System.out.println("spring");
                break;
        } // switch
    } // main
} // class
