package com.study.Ex02Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Arrays;

// Bean 
// : 스프링에서 관리하는 자바 클래스 객체를 의미
// : 싱글톤이며, 같은 이름의 빈을 중복해서 생성 불가
// Bean 역할
// - 기본적으로 싱글톤 지원
// - Dead 되면, 자동 복구됨
// - 자동 의존성 주입(DI - Dependency Injection) = 제어의 역전(IOC - Inverse of Control)
//   -> 개발자가 직접 객체를 생성(new)하지 않고, 프레임워크가 생성(관리)해주는 것을 사용하는 것
//   -> 객체 관리로부터 자유로움
//   -> 개발자가 A > B > C 생성하지 않고, 스프링이 C에 B가 필요하면 생성, A가 필요하면 생성

// Annotation : 자바 코드에 붙이는 메타 데이터로서 컴파일러에게 정보 제공하는 심볼
// @SpringBootApplication
// : 3가지 어노테이션이 붙어있는 어노테이션
// : 기본적인 스프링부트 앱 개발환경과 설정을 다해줌
// : @SpringBootConfiguration -> @Configuration이 붙은 클래스를 찾아서 설정 클래스로 등록
// : @EnableAutoConfiguration -> Spring 프레임워크의 기본적인 기능 활성화하는 애너테이션
// : @ComponentScan -> @Component가 붙은 클래스를 찾아서 Bean으로 등록

// Bean 만드는 방법
// - @Configuration + @Bean : Configuration 클래스 안에서 사용되고, 주고 외부 라이브러리를 사용시 사용
// - @Component + @Autowired : 주로 개발자가 직접 만든 클래스를 Bean으로 등록하기 위해 사용
@Configuration
class MyConfig {
	// 외부 라이브러리(Java표준) Bean 등록
	@Bean
	public java.util.Random random() {
		return new java.util.Random();
	}
}
@Component
class Student {
	String name = "홍길동";

	@Autowired // Bean 주입(의존성 주입)
	java.util.Random random;
}

@SpringBootApplication
public class Ex02BeanApplication {
	public static void main(String[] args) {
		System.out.println("스프링 애플리케이션 시작!");

		// ApplicationContext : 앱 정보를 가지고 있는 객체
		// Spring이 관리하는 모든 Bean, 환경 정보, 설정 등을 가지고 있음
		ApplicationContext context = SpringApplication.run(Ex02BeanApplication.class, args);

		// 스프링 컨테이너(Bean 저장소)에 등록된 Bean 목록 출력
		// myConfig, student, random
		String[] beanDefinitionNames = context.getBeanDefinitionNames(); // 앱 정보를 문자열 배열로 가져옴
		System.out.println(Arrays.toString(beanDefinitionNames)); // 배열의 문자열로 객체 호출

		String[] beanNames = context.getBeanDefinitionNames();  // 앱 정보를 문자열 배열로 가져옴
		Arrays.sort(beanNames); // 배열은 내림차순 정렬
		for (String beanName : beanNames) { // 배열을 향상된 for문으로 호출
			if (beanName.equals("random")) { // 배열안에 "random"과 동일한 문자열이 있으면
				System.out.println(beanName); // 그 문자열 호출
			}
		}

		// random, sutdent 빈 가져오기
		// context : 앱 정보를 가지고 있는 ApplicationContext타입의 객체 변수
		// .getBean() : Bean객체를 가져오는 메서드
		// .getBean(클래스명.class) : 특정 타입의 Bean 가져오기
		java.util.Random randomNum = context.getBean(java.util.Random.class);
		System.out.println(randomNum.nextInt(10));
		Student student = context.getBean(Student.class);
		System.out.println(student.name);
		System.out.println(student.random.nextInt(10));
	}
}
