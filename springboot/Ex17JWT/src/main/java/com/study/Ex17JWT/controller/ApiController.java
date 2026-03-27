package com.study.Ex17JWT.controller;

import com.study.Ex17JWT.config.JwtUtil;
import com.study.Ex17JWT.dto.UserDto;
import com.study.Ex17JWT.dto.UserRequestDto;
import com.study.Ex17JWT.service.impl.UsersServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

// JSON 문자열 응답 controller : REST API Server
@RestController  // @Controller + @ResponseBody
@RequestMapping("/api/users")  // 중복제거, 유지보수 용이
@RequiredArgsConstructor
public class ApiController {
    private final UsersServiceImpl usersService;
    private final JwtUtil jwtUtil;

    // HTTP 요청 데이터 받는 방법 3가지
    // 1.@RequestParam: 문자열로 하나씩 받음 - form데이터 단일값
    // 2.@RequestBody: DTO 클래스/맵 바인딩 - fetch함수
    // 3.@ModelAttribute: DTO 클래스/맵 바인딩 - form
    // 4.@PathVariable: 경로 문구로 데이터 바인딩

    // HTTP 응답 데이터 받는 방법 4가지
    // 1.Model: thymeleaf 전달
    // 2.@ResponseBody: 문자열 전달("{'id','hong'}"),
    //                  클래스/맵 객체로 전달("{'id':'hong'},{'password':'1234'}"),
    //                  JS(<script>...</script>)
    // 3.redirect:/URL

    // 회원가입
    @PostMapping("/signup")
    public UserDto createUser(UserRequestDto requestDto) {
        System.out.println(" >> 회원가입 Ok: " + requestDto.getEmail());

        // DB에 저장하는 서비스에 dto 전달
        
        return usersService.createUser(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@ModelAttribute UserRequestDto requestDto) {
        System.out.println(" >> 로그인 시도: " + requestDto.getEmail());

        UserDto foundDto = null;
        try {
            // DB에 있는 회원정보를 조회해서, 아이디/비밀번호가 맞는지 확인
            foundDto = usersService.findByEmailAndPassword(requestDto.getEmail(), requestDto.getPassword());
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println(" >> 로그인 오류: " + e.getMessage());
            return "이메일/비밀번호에 맞는 회원이 없습니다.";
        }

        // 아이디/비밀번호가 맞으면, JWT 토큰 발행
        return jwtUtil.createToken(foundDto.getEmail(),
                Arrays.asList(foundDto.getUserRole().getValue()));
    }

    // 마이페이지
    @GetMapping("/mypage")
    // 보안(인가) 등급 설정으로 USER와 ADMIN만 접근 가능
    // UserDetailsServiceImpl이 반환한 Users의 getAuthorities()에 권한값을 확인
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public UserDto myPage(Authentication authentication) throws Exception {
        // authentication = UserDetailsServiceImpl - Users객체 받음
        if (authentication == null) {
            throw new BadCredentialsException("회원 인증정보 알수없음");
        }

        System.out.println(">> 마이페이지: " + authentication.getName());

        return usersService.findUser(authentication.getName());
    }

    // 관리자페이지
    @GetMapping("/admin")
    @Secured({"ROLE_ADMIN"})
    public List<UserDto> admin() {
        return usersService.findAll();
    }
}
