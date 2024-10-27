package com.example.demo.repository;

import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProducerRepositoryTest {
    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerRepositoryTest(ProductRepository productRepository, ProducerRepository producerRepository) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
    }


    @Test
    @Transactional
    public void createSingle() {
        Product product1 = this.productRepository.save(Product.builder()
                .productName("테스 제춤")
                .productPrice(BigDecimal.valueOf(12.10))
                .productStock(24)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Product product2 = this.productRepository.save(Product.builder()
                .productName("bbb")
                .productPrice(BigDecimal.valueOf(12.10))
                .productStock(24)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Product product3 = this.productRepository.save(Product.builder()
                .productName("ccc")
                .productPrice(BigDecimal.valueOf(12.10))
                .productStock(24)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        List<Product> products1 = new ArrayList<Product>();
        products1.add(product1);
        products1.add(product2);

        List<Product> products2 = new ArrayList<Product>();
        products2.add(product2);
        products2.add(product3);

        Producer producer1 = this.producerRepository.save(Producer.builder()
                .producerCode("code222")
                .producerName("name2323")
                .products(products1)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Producer producer2 = this.producerRepository.save(Producer.builder()
                .producerCode("code333")
                .producerName("name2323")
                .products(products2)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        this.producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

        Optional<Producer> selectedProducer1 = this.producerRepository.findById(producer1.getId());
        if(selectedProducer1.isPresent()) {
            Producer producer = selectedProducer1.get();
            System.out.println(producer.getProducts());
        }

        Optional<Producer> selectedProducer2 = this.producerRepository.findById(producer2.getId());
        if(selectedProducer2.isPresent()) {
            Producer producer = selectedProducer2.get();
            System.out.println(producer.getProducts());
        }
    }

    @Test
    @Transactional
    public void createMutable() {
        Product product1 = this.productRepository.save(Product.builder()
                .productName("createMutable1")
                .productPrice(BigDecimal.valueOf(12.10))
                .productStock(24)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Product product2 = this.productRepository.save(Product.builder()
                .productName("createMutable2")
                .productPrice(BigDecimal.valueOf(12.10))
                .productStock(24)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Product product3 = this.productRepository.save(Product.builder()
                .productName("createMutable3")
                .productPrice(BigDecimal.valueOf(12.10))
                .productStock(24)
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Producer producer1 = this.producerRepository.save(Producer.builder()
                .producerCode("code4")
                .producerName("name4")
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        Producer producer2 = this.producerRepository.save(Producer.builder()
                .producerCode("code5")
                .producerName("name5")
                .createdAt(LocalDateTime.now())
                .createdBy("james")
                .updatedAt(LocalDateTime.now())
                .updatedBy("james").build()
        );

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        this.producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        this.productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        System.out.println(this.producerRepository.findById(1L).get().getProducts());
        System.out.println(this.productRepository.findById(2L).get().getProducers());
    }

}
