package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryRepositoryTest {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryRepositoryTest(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Test
    public void create() {
        List<Product> products = new ArrayList<Product>();
        products.add(this.productRepository.save(Product.builder()
                .productName("신규 상품 카테고리4")
                .productPrice(BigDecimal.valueOf(19))
                .productStock(20)
                .createdBy("a")
                .createdAt(LocalDateTime.now())
                .updatedBy("a")
                .updatedAt(LocalDateTime.now()).build())
        );
        products.add(this.productRepository.save(Product.builder()
                .productName("신규 상품 카테고리5")
                .productPrice(BigDecimal.valueOf(19))
                .productStock(20)
                .createdBy("a")
                .createdAt(LocalDateTime.now())
                .updatedBy("a")
                .updatedAt(LocalDateTime.now()).build())
        );

        Category category = this.categoryRepository.save(Category.builder()
                .categoryCode("code2")
                .categoryName("category 1")
                .products(products)
                .createdAt(LocalDateTime.now())
                .createdBy("a")
                .updatedAt(LocalDateTime.now())
                .updatedBy("b").build()
        );

        products = new ArrayList<Product>();
        Optional<Category> selectedCategory = this.categoryRepository.findById(category.getId());
        if(selectedCategory.isPresent()) {
            products = selectedCategory.get().getProducts();
        }

        products.forEach((product) -> {
            System.out.println(product.getProductName());
        });
    }

}
