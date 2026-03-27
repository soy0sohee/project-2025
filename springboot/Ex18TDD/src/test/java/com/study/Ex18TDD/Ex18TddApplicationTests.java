package com.study.Ex18TDD;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// @StringBootTest: 테스트에서 전체 앱 컨텍스트 로드해 주는 핵심 어노테이션
//   -> @BootstrapWith: ApplicationContext(빈 관리) 초기화 방법 설정
//   -> @ExtendWith: Junit5와 Spring을 연결
@SpringBootTest
class Ex18TddApplicationTests {

	// @Test: 단위 테스트 케이스임을 알려주는 어노테이션
	@Test
	// contextLoads(): Application Context가 정상 로드되었음을 알림
	void contextLoads() {
		System.out.println(" >> 테스트 준비 완료!");
	}

}
