package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition;

import java.util.Set;

public class PurchaseRequisition {

    private long id;

    private Set<ProductToBeOrdered> listOfProducts;

    private PurchaseRequisitionProcessStatus status;

    public PurchaseRequisition(PurchaseRequisitionProcessStatus status, Set<ProductToBeOrdered> listOfProductsToBeOrdered) {
        this.listOfProducts = listOfProductsToBeOrdered;
        this.status = status;
    }
}
