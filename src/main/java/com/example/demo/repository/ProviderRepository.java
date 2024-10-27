package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Product> findAllById(Long id);
}
