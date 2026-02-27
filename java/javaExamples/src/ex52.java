import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int kor;
    int eng;
    int math;

    public Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    public String toString() {
        return name + " :" +
                " kor = " + kor +
                " eng = " + eng +
                " math = " + math +
                " 합계 = " + Math.abs(kor + eng + math) +
                " 평균 = " + Math.round(kor + eng + math / 3);
    }
    public String getName() {
        return name;
    }
}

public class ex52 {
    // ArrayList<클래스 or 타입> : 배열에 특정 객체를 담음
    public static ArrayList<Student> scoreList = new ArrayList<>();

    public static void main(String[] args) {
        // 연습문제
        // ArrayList 클래스 객체 배열을 사용해보자.
        // 입력 및 출력 예시
        // -----------성적 관리 프로그램-------------
        // 1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : 1
        // 이름 입력 : 홍길동
        // 국어점수 입력 : 70
        // 영어점수 입력 : 80
        // 수학점수 입력 : 90

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("1.입력 2.전체출력 3.검색 4.수정 5.삭제 6.종료 : ");
            int programValue = scanner.nextInt();

            if (programValue == 1) {
                System.out.print("이름 입력 : ");
                String name = scanner.next();

                System.out.print("국어점수 입력 : ");
                int kor = scanner.nextInt();

                System.out.print("영어점수 입력 : ");
                int eng = scanner.nextInt();

                System.out.print("수학점수 입력 : ");
                int math = scanner.nextInt();

                scoreList.add(new Student(name, kor, eng, math));
                System.out.println(scoreList);
                System.out.println();
            } else if (programValue == 2) {
                for(Student list : scoreList){
                    System.out.println(list);
                    System.out.println();
                }
            } else if (programValue == 3) {
                System.out.print("이름 입력 : ");
                String name = scanner.next();

                for(int i = 0; i < scoreList.size(); i++){
                    if(scoreList.get(i).getName().equals(name)){
                        System.out.println(scoreList.get(i));
                        System.out.println();
                    }
                }
            } else if (programValue == 4) {
                System.out.print("이름 입력 : ");
                String name = scanner.next();

                for(int i = 0; i < scoreList.size(); i++){
                    if(scoreList.get(i).getName().equals(name)){
                        System.out.print("국어점수 입력 : ");
                        int kor = scanner.nextInt();

                        System.out.print("영어점수 입력 : ");
                        int eng = scanner.nextInt();

                        System.out.print("수학점수 입력 : ");
                        int math = scanner.nextInt();

                        scoreList.set(i, new Student(name, kor, eng, math));
                        System.out.println(scoreList);
                        System.out.println();
                    }
                }
            } else if (programValue == 5) {
                System.out.print("이름 입력 : ");
                String name = scanner.next();

                for(int i = 0; i < scoreList.size(); i++) {
                    if(scoreList.get(i).getName().equals(name)){
                        scoreList.remove(i);
                        System.out.println(scoreList);
                        System.out.println();
                    }
                }
            } else if (programValue == 6) {
                System.out.println("프로그램이 종료되었습니다.");
                scanner.close();
                break;
            } // if
        } // while
    } // main
} // class
