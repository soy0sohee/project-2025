import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ex46 {
    public static void main(String[] args) {
        // 파일 쓰기/읽기
        // try - with - resources문 : JDK7이상 지원
        try(FileWriter file = new FileWriter("data.txt")){
            file.write("안녕하세요");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // 파일 경로 지정시 폴더 구분
        // 윈도우즈 : \ (자바문자열에서는 \가 특수문자)
        // 리눅스/MacOS : /
        // 리눅수 : "/data.txt"
        try(FileWriter file = new FileWriter(".\\src\\data.txt")){
            file.write("안녕하세요");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        try(FileReader file = new FileReader("data.txt")){
            int data = 0;
            do {
                data = file.read();
                if (data != -1) { // -1 = end of file 파일의 끝
                    System.out.println((char)data);
                }
            } while (data != -1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
