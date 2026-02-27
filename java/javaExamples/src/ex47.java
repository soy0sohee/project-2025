import java.io.BufferedReader;
import java.io.FileReader;

public class ex47 {
    public static void main(String[] args) {
        // 연습문제
        // 철수 = 초등학교 교사
        // 성적관리 프로그램 작성하고자함
        // 학생 = 영희, 수만, 순신
        // 과목 = 영어, 수학, 국어
        // score.txt에 저장하고 읽어오는 프로그램

        // 파일형식
        // 이름 영어 수학 국어
        // 영희 100 80 70
        // 수만 80 80 70
        // 순신 90 80 70

        // 학생 이름과 과목 Scanner로 입력
        // 점수를 출력하는 프로그램
        // 입력 = 영희 영어
        // 출력 = 100

        // FileReader : 바이트 단위로 읽어오기
        // BufferedReader : \n 문자까지 한줄에 읽어오기

        try {
            FileReader fReader = new FileReader("score.txt");
            BufferedReader bReader = new BufferedReader(fReader);
            String line;

            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
            }

            bReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Exception 자주 발생하는 곳
        // 1. 파일 처리할 떄
        // 2. 통신
        // 3. 형변환(10진수 -> 2진수)
    }
}
