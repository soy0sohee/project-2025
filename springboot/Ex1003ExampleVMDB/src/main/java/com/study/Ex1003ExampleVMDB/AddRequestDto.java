package com.study.Ex1003ExampleVMDB;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class AddRequestDto {
    private String productName;
    private Integer productPrice;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate productLimitDate;
}
