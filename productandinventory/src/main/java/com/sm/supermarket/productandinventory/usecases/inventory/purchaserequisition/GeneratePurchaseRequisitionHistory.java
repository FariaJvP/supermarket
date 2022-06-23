package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistoryStatusUpdate;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.EntityRepositoryForPurchaseRequisitionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GeneratePurchaseRequisitionHistory {

    private PurchaseRequisitionHistory purchaseRequisitionHistory;

    private final EntityRepositoryForPurchaseRequisitionHistory purchaseRequisitionrepository;

    @Autowired
    public GeneratePurchaseRequisitionHistory(EntityRepositoryForPurchaseRequisitionHistory repository){
        this.purchaseRequisitionrepository = repository;
    }

    public void execute(PurchaseRequisition purchaseRequisition, LocalDateTime requestDateAndTime) {
        this.purchaseRequisitionHistory = new PurchaseRequisitionHistory(purchaseRequisition, PurchaseRequisitionHistoryStatusUpdate.PENDING, requestDateAndTime);
        purchaseRequisitionrepository.sendToDatabase(purchaseRequisitionHistory);
    }
}
