package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ProductRepositoryTest {
    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Test
    public void create() {
        Product product = this.productRepository.save(Product.builder()
                .productName("제품1")
                .productPrice(BigDecimal.valueOf(100.00))
                .productStock(10)
                .createdBy("james")
                .createdAt(LocalDateTime.now())
                .updatedBy("james")
                .updatedAt(LocalDateTime.now()).build());
    }
}
