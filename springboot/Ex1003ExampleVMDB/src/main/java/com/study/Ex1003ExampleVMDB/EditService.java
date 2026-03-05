package com.study.Ex1003ExampleVMDB;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EditService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void editProduct(Long productNo, EditRequestDto editRequestDto) {
        ProductEntity productEntity = productRepository.findById(productNo).orElseThrow();

        String productName = editRequestDto.getProductName();
        Integer productPrice = editRequestDto.getProductPrice();
        LocalDate productLimitDate = editRequestDto.getProductLimitDate();

        productEntity.updateProduct(productName, productPrice, productLimitDate);
        productRepository.save(productEntity);
    }
}
