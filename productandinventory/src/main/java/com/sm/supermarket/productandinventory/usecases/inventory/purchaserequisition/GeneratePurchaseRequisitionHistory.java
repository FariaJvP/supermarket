package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.EntityRepositoryForPurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratePurchaseRequisitionHistory {

    private final EntityRepositoryForPurchaseRequisitionHistory purchaseRequisitionHistoryrepository;

    @Autowired
    public GeneratePurchaseRequisitionHistory(EntityRepositoryForPurchaseRequisitionHistory repository){
        this.purchaseRequisitionHistoryrepository = repository;
    }

    public void execute(PurchaseRequisitionHistory purchaseRequisitionHistory) {
        purchaseRequisitionHistoryrepository.sendToDatabase(purchaseRequisitionHistory);
    }
}
