package com.sm.supermarket.productandinventory.web.dto;

import java.math.BigDecimal;

public class NewProductRequest {

    private String name;

    private Long brandId;

    private String description;

    private BigDecimal price;

    private String unit;

    public NewProductRequest(String name, Long brandId, String description, BigDecimal price, String unit) {
        this.name = name;
        this.brandId = brandId;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public Long getBrandId() {
        return this.brandId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getUnit() {
        return this.unit;
    }
}
