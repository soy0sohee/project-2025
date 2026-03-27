package com.study.Ex18TDD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @StringBootTest: 전체 애플리케이션 로딩, 통합테스트에 용이함
// @WebMvcTest: 컨트롤러 레이어만 로딩, 단위테스트에 용이함
@WebMvcTest(ApiController.class)
class ApiControllerTest {
    // 1. 가상(Mock)의 HTTP 요청을 만들어서 테스트
    // 2. 응답 결과 확인

    @Autowired // 가상의 HTTP 요청 생성
    MockMvc mockMvc;
    @MockitoBean // Controller에서 주입받은 Bean 객체를 Mock 형태로 객체 생성
    MemberService memberService;

    // WebAppContext: 스프링 컨테이너 + HTTP 요청/응답 처리
    // ApplicationContext: 스프링 컨테이너
    @Autowired
    WebApplicationContext ctx;

    @BeforeEach // 각 테스트마다 시작 전 한번씩 호출
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    void loginAction() throws Exception {
        // BDD: Behavior Driven Development 행동주도개발
        // given(상태)-when(조건)-then(검증) 패턴
        // given: loginAction함수가 동작하는 조건 기술
        given(memberService.loginAction(new MemberDto("hong", "1234"))).willReturn(1);
        ResDto resDto = ResDto.builder()
                .status("ok")
                .message("로그인 성공!")
                .build();
        MemberDto dto = MemberDto.builder()
                .loginId("hong")
                .loginPw("1234")
                .build();
        // when, then: 기대하는 응답 DTO 생성
        // HTTP 요청을 날리고 테스트
        mockMvc.perform(post("/api/v1/loginAction")
                // Jackson Library 사용
                // 객체 내용을 문자열로 바꿈(직렬화, 역직렬화)
                .content(new ObjectMapper().writeValueAsString(dto))
                // content-type 헤더 설정
                .contentType(MediaType.APPLICATION_JSON)) // when
                .andExpect(status().isOk()) // 응답코드 200
                .andExpect(jsonPath("$.status").value("ok"))
                .andExpect(jsonPath("$.message").exists()) // then
                .andDo(print());

        // verity: 해당 객체의 메서드가 실행되었는지 체크
        verify(memberService).loginAction(new MemberDto("hong","1234"));
    }
}