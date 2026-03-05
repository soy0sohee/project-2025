package com.study.Ex1003ExampleVMDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/edit")
public class EditController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EditService editService;

    @GetMapping("/data")
    public EditResponseDto data(@RequestParam Long productNo) {
        ProductEntity productEntity = productRepository.findById(productNo).orElseThrow();
        String productName = productEntity.getProductName();
        Integer productPrice = productEntity.getProductPrice();
        LocalDate productLimitDate = productEntity.getProductLimitDate();
        return new EditResponseDto(productName, productPrice, productLimitDate);
    }

    @PostMapping("/product")
    @ResponseBody
    public String edit(@RequestParam Long productNo, @RequestBody EditRequestDto editRequestDto){
        editService.editProduct(productNo, editRequestDto);
        return "ok";
    }
}
