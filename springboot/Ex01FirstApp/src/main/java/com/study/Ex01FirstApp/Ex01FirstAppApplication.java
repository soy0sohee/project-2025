package com.study.Ex01FirstApp;
// package
// : 폴더 경로를 다르게 함으로 동일한 클래스 이름을 구분하는것
// : 클래스 이름은 동일해도, 다른 패키지(폴더)에 있으므로 동일한 이름의 클래스를 사용할 수 있음

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// autoconfigure : 라이브러리들의 디펜던시(버전 호환성 체크)

@SpringBootApplication
public class Ex01FirstAppApplication {

	public static void main(String[] args) {
		// String[] args
		// : String[] -> 문자열 담는 배열
		// : args -> 배열 안에 들어오는 값들
		// : 프로그램 구동시 주는 파라미터 -> 메인 메서드로 전달되는 문자열의 모음
		// 예) 한컴오피스 (hwp.exe[] 문서1.hwp 문서2.hwp)
		// 예) java -version

		SpringApplication.run(Ex01FirstAppApplication.class, args);
		// SpringApplication.run : 스프링 앱 실행 함수
		// Ex01FirstAppApplication.class : 클래스 정보를 담는 객체
	}

}
