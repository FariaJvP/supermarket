package com.sm.supermarket.productandinventory.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class ProductToBeOrderedRequisition {

    @NotNull
    @JsonProperty private long productId;

    @NotNull @Positive(message = "quantity must not be negative or zero")
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
