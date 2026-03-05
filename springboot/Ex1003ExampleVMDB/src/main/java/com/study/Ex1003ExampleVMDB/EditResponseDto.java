package com.study.Ex1003ExampleVMDB;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditResponseDto {
    private String productName;
    private Integer productPrice;
    private LocalDate productLimitDate;

    public EditResponseDto(String productName, Integer productPrice, LocalDate productLimitDate){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productLimitDate = productLimitDate;
    }
}
