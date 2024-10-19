package com.product_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.product_service.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
