package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {
    private final ProviderRepository providerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProviderRepositoryTest(ProviderRepository providerRepository, ProductRepository productRepository) {
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
    }


    @Test
    public void create() {
        Provider provider = this.providerRepository.save(Provider.builder()
                .providerName("james.com")
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build());

        Product product3 = this.productRepository.save(Product.builder()
                .productName("상품명3")
                .productPrice(BigDecimal.valueOf(123.80))
                .productStock(1)
                .provider(provider)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build());

        Product product2 = this.productRepository.save(Product.builder()
                .productName("상품명2")
                .productPrice(BigDecimal.valueOf(123.80))
                .productStock(1)
                .provider(provider)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build());

        List<Product> products = this.providerRepository.findById(provider.getId()).get().getProducts();
        products.forEach((product) -> {
            System.out.println(product.getProductName());
        });
    }
}
