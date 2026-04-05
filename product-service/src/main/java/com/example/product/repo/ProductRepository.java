package com.example.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
