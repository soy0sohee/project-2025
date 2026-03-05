package com.study.Ex1003ExampleVMDB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="product_no")
    private Long productNo;
    @Column(name="product_name")
    private String productName;
    @Column(name="product_price")
    private Integer productPrice;
    @Column(name="product_limit_date")
    private LocalDate productLimitDate;

    public void updateProduct(String productName, Integer productPrice, LocalDate productLimitDate){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productLimitDate = productLimitDate;
    }
}
