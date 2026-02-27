package com.study.EX14RestAPI;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// @RestController = @Controller + @ResponseBody 합쳐놓은 것
// 클래스 이름 위에 @ResponseBody를 넣으면, 클래스 안의 모든 메서드는 @ResponseBody 적용을 받음
@RestController
// 클래스 안의 모든 메서드는 "/api/v1" 경로로 시작
@RequestMapping("/api/v1")
public class ApiController {
    // URI : http://localhost:8080/api/v1/hello
    @RequestMapping("/hello")
    public String hello(){
        return "Hello, 저는 API 서버";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(@RequestBody String body){
        System.out.println(body);
        Map<String, String> resMap = new HashMap<>();
        resMap.put("status","ok");
        resMap.put("message","로그인 성공");
        return resMap;
    }

    @PostMapping("/loginDto")
    @ResponseBody
    public ResDto loginDto(@RequestBody ReqDto dto){
        System.out.println(dto.getUsername());

        ResDto resDto = new ResDto();
        resDto.setStatus("success");
        resDto.setMessage("로그인 성공");

        return resDto;
    }
}
