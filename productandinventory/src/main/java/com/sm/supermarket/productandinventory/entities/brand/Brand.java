package com.sm.supermarket.productandinventory.entities.brand;

import javax.persistence.*;

@Entity
@Table(name = "tb_brand")
public class Brand {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long id;

    @Column(name = "brand_name", nullable = false, unique = true, length = 255)
    private String name;
}
