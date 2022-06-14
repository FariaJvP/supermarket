package com.sm.supermarket.productandinventory.infrastructure.springdatarepositories;

import com.sm.supermarket.productandinventory.entities.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
