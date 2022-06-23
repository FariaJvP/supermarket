package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PurchaseRequisitionForm {

    private List<ProductToBeOrderedRequisition> listOfProductsToBeOrdered;

    private LocalDateTime dateTime;

    @Deprecated
    public PurchaseRequisitionForm(){

    }

    public PurchaseRequisitionForm(List<ProductToBeOrderedRequisition> listOfProductsToBeOrdered, LocalDateTime dateTime) {
        this.listOfProductsToBeOrdered = listOfProductsToBeOrdered;
        this.dateTime = dateTime;
    }

    public List<ProductToBeOrderedRequisition> getListOfProductsToBeOrdered() {
        return listOfProductsToBeOrdered;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
