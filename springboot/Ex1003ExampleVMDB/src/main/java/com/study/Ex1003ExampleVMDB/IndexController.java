package com.study.Ex1003ExampleVMDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/list")
    public IndexResponseDto list(){
        long count = productRepository.count();
        List<ProductEntity> list = productRepository.findAll();
        return new IndexResponseDto(list, count);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam Long productNo){
        productRepository.deleteById(productNo);
        return "ok";
    }
}
