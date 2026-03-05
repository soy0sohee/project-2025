package com.study.Ex1003ExampleVMDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/add")
public class AddController {
    @Autowired
    private AddService addService;

    @GetMapping("/product")
    public String product(){
        return "redirect:/";
    }
    @PostMapping("/product")
    @ResponseBody
    public String productForm(@RequestBody AddRequestDto addRequestDto){
        addService.addProduct(addRequestDto);
        System.out.println(addRequestDto.getProductName());
        return "ok";
    }
}
