package com.study.Ex1000ExampleCounter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private CounterBean counter;

    // index - 일반 URL 요청
    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("count", counter.getCount());
        return "index";
    }
    @GetMapping("/plus")
    public String plus(Model model){
        counter.setCount(counter.getCount() + 1);
        model.addAttribute("count", counter.getCount());
        return "redirect:/";
    }
    @GetMapping("/minus")
    public String minus(Model model){
        counter.setCount(counter.getCount() - 1);
        model.addAttribute("count", counter.getCount());
        return "redirect:/";
    }

    // index - fetch(), API 요청
    @GetMapping("/countApi")
    public String countApi(){
        return "countApi";
    }
    @GetMapping("/api/plus")
    @ResponseBody
    public String apiPlus(){
        counter.setCount(counter.getCount() + 1);
        return String.valueOf(counter.getCount());
    }
    @GetMapping("/api/minus")
    @ResponseBody
    public String apiMinus(){
        counter.setCount(counter.getCount() - 1);
        return String.valueOf(counter.getCount());
    }

    // count - JS 버전
    @GetMapping("/count")
    public String count(){
        return "count";
    }
}
