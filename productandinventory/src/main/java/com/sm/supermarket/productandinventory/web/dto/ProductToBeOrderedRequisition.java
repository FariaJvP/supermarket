package com.sm.supermarket.productandinventory.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class ProductToBeOrderedRequisition {

    @JsonProperty private long productId;

    @JsonProperty private BigInteger quantity;

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
