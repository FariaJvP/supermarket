package com.sm.supermarket.productandinventory.web.dto;

import com.sm.supermarket.productandinventory.entities.brand.Brand;
import com.sm.supermarket.productandinventory.web.validation.ValidBrand;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class NewProductRequest {

    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "brand id must not be null")
    @ValidBrand(fieldName = "id", domainClass = Brand.class)
    private Long brandId;

    @NotBlank(message = "description must not be blank")
    private String description;

    @NotNull(message = "price must not be null")
    @PositiveOrZero(message = "price can not be negative")
    private BigDecimal price;

    @NotBlank(message = "unit must not be blank")
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
