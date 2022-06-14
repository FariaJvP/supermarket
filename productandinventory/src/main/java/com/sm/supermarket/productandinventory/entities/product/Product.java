package com.sm.supermarket.productandinventory.entities.product;

import com.sm.supermarket.productandinventory.entities.brand.Brand;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", length = 100, nullable = false)
    private String name;

    @ManyToOne @JoinColumn(name = "brand_id", referencedColumnName = "brand_id", nullable = false)
    private Brand brand;

    @Column(name = "product_description", length = 200, nullable = false)
    private String description;

    @Column(name = "product_price", columnDefinition = "DECIMAL(15,2) NOT NULL CHECK(product_price >= 0)")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_unit", length = 4, nullable = false)
    private PackageUnit unit;

    @Deprecated
    public Product(){

    }

    public Product(String name, Brand brand, String description, BigDecimal price, PackageUnit unit) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.unit = unit;

    }
}