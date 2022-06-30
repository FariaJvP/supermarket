package com.sm.supermarket.productandinventory.web.dto;

import com.sm.supermarket.productandinventory.entities.brand.Brand;
import com.sm.supermarket.productandinventory.web.validation.ValidBrand;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class ProductToBeOrderedRequisition {


    @NotNull public long productId;

    @Positive(message = "{product.quantity.positive}")
    @NotNull public BigInteger quantity;

    public ProductToBeOrderedRequisition(long productId, BigInteger quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return this.productId;
    }

    public BigInteger getQuantity() {
        return this.quantity;
    }
}
