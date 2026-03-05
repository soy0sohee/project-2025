package com.study.Ex1003ExampleVMDB;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IndexResponseDto {
    private List<ProductEntity> list;
    private Long count;

    public IndexResponseDto(List<ProductEntity> list, long count){
        this.list = list;
        this.count = count;
    }
}
