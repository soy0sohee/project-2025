package com.study.ExSBB;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String root() {
        //localhost:8080 접속 -> root() 메서드 실행 -> localhost:8080/question/list 리다이렉트
        return "redirect:/question/list";
    }
}
