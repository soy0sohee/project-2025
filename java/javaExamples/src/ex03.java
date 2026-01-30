public class ex03 {
    public static void main(String[] args) {
        // JS 동적 타입 언어 : 변수(리터럴)에 대입 시 데이터 타입 결정
        //  - let a = 10; number type
        // JAVA 정적 타입 언어 : CODE에서 데이터 타입 결정
        //  - int a = 10;

        // 데이터 타입(Data type)
        // 숫자 타입(JS number)
        //  - 정수형 : int(4byte, +-21억)
        int myInt = 10;
        System.out.println("myInt = " + myInt);
        //  - 정수형 : long(8byte)
        long myLong = 20L; // 값L : 값을 8바이트 Long으로 초기화
        System.out.println("myLong = " + myLong);
        //  - 정수형 : short(2byte, +-32767)
        short myShort = 30; // short 지정범위 초과 시 4바이트 int로 임시 변경 후 short로 형 변환
        System.out.println("myShort = " + myShort);
        //  - 정수형 : byte(1byte, +-127)
        byte myByte = 40;
        System.out.println("myByte = " + myByte);

        //  - 실수형 : float(4byte)
        float myFloat = 3.14f; // 값f : f를 리터널에 넣어줘야 함
        System.out.println("myFloat = " + myFloat);
        //  - 실수형 : double(8byte)
        double myDouble = 6.14; // double로 초기화
        System.out.println("myDouble = " + myDouble);

        // 부울 타입(JS boolean)
        //  - boolean(1byte)
        boolean myBool = true;
        System.out.println("myBool = " + myBool);

        // 문자 타입(JS string)
        //  - Char(2byte)
        char myChar = '가'; // 단따옴표만 가능
        System.out.println("myChar = " + myChar);
        // 유니코드 값 반환
        System.out.println("(int)myChar = " + (int) myChar); // (데이터타입)값 : 형변환
        System.out.println("(char)0xAC00 = " + (char) 0xAC00);
        // 유니코드표의 첫장은 아스키코드표
        System.out.println("(int)A = " + (int) 'A');
        System.out.println("(char)65 " + (char) 65);

        // 문자열 타입(JS string)
        //  - String : 가변길이, S 대문자 필수
        String myString = "가나다";
        System.out.println("myString = " + myString);

        // 코드 재정렬(JS Prettier Formatter)
        // Ctrl + Alt + L

        // 전체 프로젝트 검색(특정 문자열로 코드 찾음)
        // Ctrl + Shift + F
    }
}




















