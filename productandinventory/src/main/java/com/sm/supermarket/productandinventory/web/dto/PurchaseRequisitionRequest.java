package com.sm.supermarket.productandinventory.web.dto;

import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.PurchaseRequisitionForm;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Set;

public class PurchaseRequisitionRequest {

    @Valid
    private Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered;

    private LocalDateTime dateTime;

    @Deprecated
    public PurchaseRequisitionRequest (){ }

    public PurchaseRequisitionRequest(Set<ProductToBeOrderedRequisition> products) {
        this.listOfProductsToBeOrdered = products;
        this.dateTime = LocalDateTime.now();
    }

    public PurchaseRequisitionForm convertIntoPurchaseRequisitionForm() {
        return new PurchaseRequisitionForm(listOfProductsToBeOrdered, dateTime);
    }
}
