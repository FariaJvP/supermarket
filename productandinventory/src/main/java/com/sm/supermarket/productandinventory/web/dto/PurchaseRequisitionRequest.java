package com.sm.supermarket.productandinventory.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class PurchaseRequisitionRequest {

    @JsonProperty private long productId;

    @JsonProperty private BigInteger quantity;

    public PurchaseRequisitionRequest(long productId, BigInteger quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return this.productId;
    }

    public BigInteger getQuantityToBeOrdered() {
        return this.quantity;
    }
}
