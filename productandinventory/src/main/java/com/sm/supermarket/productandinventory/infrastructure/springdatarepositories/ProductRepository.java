package com.sm.supermarket.productandinventory.infrastructure.springdatarepositories;

import com.sm.supermarket.productandinventory.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> { }
