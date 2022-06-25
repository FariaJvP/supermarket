package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class PurchaseRequisitionForm {

    private Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered;

    private LocalDateTime dateTime;

    @Deprecated
    public PurchaseRequisitionForm(){

    }

    public PurchaseRequisitionForm(Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered, LocalDateTime dateTime) {
        this.listOfProductsToBeOrdered = listOfProductsToBeOrdered;
        this.dateTime = dateTime;
    }

    public Set<ProductToBeOrderedRequisition> getListOfProductsToBeOrdered() {
        return listOfProductsToBeOrdered;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
