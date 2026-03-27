package com.study.ExBlog.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        Person exPerson = new Person();
        exPerson.setId(1L);
        exPerson.setName("홍길동");
        exPerson.setAge(11);
        exPerson.setHobbies(List.of("독서","운동"));

        model.addAttribute("person", exPerson);
        model.addAttribute("today", LocalDate.now());

        return "example";
    }

    @Setter
    @Getter
    class Person {
        private Long id;
        private String name;
        private Integer age;
        private List<String> hobbies;
    }
}
