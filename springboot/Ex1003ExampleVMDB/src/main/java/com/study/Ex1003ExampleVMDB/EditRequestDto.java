package com.study.Ex1003ExampleVMDB;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditRequestDto {
    private Long productNo;
    private String productName;
    private Integer productPrice;
    private LocalDate productLimitDate;
}
