package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ProductDetailRepositoryTest {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    @Autowired
    public ProductDetailRepositoryTest(ProductRepository productRepository, ProductDetailRepository productDetailRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
    }


    @Test
    public void create() {
        Product product = this.productRepository.save(Product.builder()
                .productName("제품13")
                .productPrice(BigDecimal.valueOf(1254.10))
                .productStock(54)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build());

        ProductDetail productDetail = this.productDetailRepository.save(ProductDetail.builder()
                .description("제품13는 좋은 제품")
                .product(product)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build());

        System.out.println(productDetail.getProduct());
    }
}
