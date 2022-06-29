package com.sm.supermarket.productandinventory.builders;

import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.PurchaseRequisitionForm;
import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;
import com.sm.supermarket.productandinventory.web.dto.PurchaseRequisitionRequest;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class PurchaseRequisitionDataBuilder {

    private final Set<ProductToBeOrderedRequisition> listOfProductsToBeOrdered = new HashSet<>();

    public PurchaseRequisitionDataBuilder withProductToBeOrdered(long id, BigInteger quantity){
        this.listOfProductsToBeOrdered.add(new ProductToBeOrderedRequisition(id,quantity));
        return this;
    }

    public PurchaseRequisitionForm buildPurchaseRequisitionForm() {
        return new PurchaseRequisitionRequest(listOfProductsToBeOrdered).convertIntoPurchaseRequisitionForm();
    }

    public PurchaseRequisitionRequest buildPurchaseRequisitionRequest() {
        return new PurchaseRequisitionRequest(listOfProductsToBeOrdered);
    }
}
