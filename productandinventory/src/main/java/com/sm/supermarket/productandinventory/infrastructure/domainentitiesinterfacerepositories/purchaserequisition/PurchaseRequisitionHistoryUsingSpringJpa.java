package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.EntityRepositoryForPurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.PurchaseRequisitionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRequisitionHistoryUsingSpringJpa implements EntityRepositoryForPurchaseRequisitionHistory {

    @Autowired
    public PurchaseRequisitionHistoryRepository purchaseRequisitionHistoryRepository;

    @Override
    public void sendToDatabase(PurchaseRequisitionHistory purchaseRequisitionHistory) {
        purchaseRequisitionHistoryRepository.save(purchaseRequisitionHistory);
    }
}
