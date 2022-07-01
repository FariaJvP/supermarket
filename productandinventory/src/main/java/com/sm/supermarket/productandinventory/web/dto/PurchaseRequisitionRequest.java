package com.sm.supermarket.productandinventory.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.PurchaseRequisitionForm;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

public class PurchaseRequisitionRequest {

    @NotNull(message = "{purchaserequisition.notnull.list}")
    @NotEmpty(message = "{purchaserequisition.notempty.list}")
    @JsonProperty @Valid
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
