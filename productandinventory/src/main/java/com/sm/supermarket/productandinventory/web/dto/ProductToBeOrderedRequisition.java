package com.sm.supermarket.productandinventory.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sm.supermarket.productandinventory.web.validation.ValidPackageUnit;
import com.sm.supermarket.productandinventory.web.validation.ValidProduct;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class ProductToBeOrderedRequisition {

    @NotNull
    @ValidProduct
    @JsonProperty private long productId;

    @NotBlank(message = "{packageunit.notblank}")
    @ValidPackageUnit
    @JsonProperty private String unit;

    @NotNull
    @Positive(message = "{product.quantity.positive}")
    @JsonProperty private BigInteger quantity;

    public ProductToBeOrderedRequisition(long productId, String unit, BigInteger quantity) {
        this.productId = productId;
        this.unit = unit;
        this.quantity = quantity;
    }

    public long getProductId() {
        return this.productId;
    }

    public BigInteger getQuantity() {
        return this.quantity;
    }

    public String getReferenceUnit() {
        return this.unit;
    }

    public void convertWeighttoGranular(BigInteger convertedWeight) {
        this.quantity = convertedWeight;
    }
}
