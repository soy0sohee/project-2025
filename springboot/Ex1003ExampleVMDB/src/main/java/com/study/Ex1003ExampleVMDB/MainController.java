package com.study.Ex1003ExampleVMDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String main() {
        return "index";
    }
    @GetMapping("/add")
    public String add() {
        return "add";
    }
    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }
}
