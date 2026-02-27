package com.study.Ex07Thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

// Thymeleaf
// : spring 프레임워크의 공식 동적 UI템플릿
// : 과거에는 JSP(Java Server Page)가 주로 동적 웹을 만들기 위해 사용되었으나,
//   최신 트렌트에는 타임리프를 기준으로 만듦

@ServletComponentScan
@SpringBootApplication
public class Ex07ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex07ThymeleafApplication.class, args);
	}

}
