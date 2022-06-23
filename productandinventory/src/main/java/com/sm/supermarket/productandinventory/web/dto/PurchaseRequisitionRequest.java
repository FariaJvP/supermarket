package com.sm.supermarket.productandinventory.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.PurchaseRequisitionForm;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseRequisitionRequest {

    @JsonProperty private final List<ProductToBeOrderedRequisition> listOfProductsToBeOrdered;

    private final LocalDateTime dateTime;


    public PurchaseRequisitionRequest(List<ProductToBeOrderedRequisition> products) {
        this.listOfProductsToBeOrdered = products;
        this.dateTime = LocalDateTime.now();
    }

    public PurchaseRequisitionForm convertIntoPurchaseRequisitionForm() {
        return new PurchaseRequisitionForm(listOfProductsToBeOrdered, dateTime);
    }
}
