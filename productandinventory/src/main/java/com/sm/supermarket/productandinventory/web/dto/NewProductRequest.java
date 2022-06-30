package com.sm.supermarket.productandinventory.web.dto;

import com.sm.supermarket.productandinventory.entities.brand.Brand;
import com.sm.supermarket.productandinventory.web.validation.ValidBrand;
import com.sm.supermarket.productandinventory.web.validation.ValidPackageUnit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NewProductRequest {

    @NotBlank(message = "{product.name.notblank}")
    @Size(max = 100, message = "{product.name.maxsize}")
    private String name;

    @ValidBrand(fieldName = "id", domainClass = Brand.class)
    @NotNull private Long brandId;

    @NotBlank(message = "{product.description.notblank}")
    @Size(max = 200, message = "{product.description.maxsize}")
    private String description;

    @NotNull(message = "{product.price.notnull}")
    @PositiveOrZero(message = "{product.price.positive}")
    private BigDecimal price;

    @NotBlank(message = "{packageunit.notblank}")
    @ValidPackageUnit
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
