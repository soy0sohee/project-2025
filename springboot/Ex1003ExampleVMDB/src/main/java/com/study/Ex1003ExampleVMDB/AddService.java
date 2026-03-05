package com.study.Ex1003ExampleVMDB;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AddService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addProduct(AddRequestDto addRequestDto){
        String productName = addRequestDto.getProductName();
        Integer productPrice = addRequestDto.getProductPrice();
        LocalDate productLimitDate = addRequestDto.getProductLimitDate();

        ProductEntity productEntity = ProductEntity.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productLimitDate(productLimitDate)
                .build();
        productRepository.save(productEntity);
    }
}
