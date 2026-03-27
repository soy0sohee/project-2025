package com.study.Ex17JWT.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// HTML 응답 controller
@Controller
public class ViewController {
    @GetMapping("/")
    public String api() {
        return "apiForm";
    }
}
