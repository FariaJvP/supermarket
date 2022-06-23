package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.EntityRepositoryForPurchaseRequisition;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.PurchaseRequisitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRequisitionRepositoryUsingSpringJpa implements EntityRepositoryForPurchaseRequisition {

    @Autowired
    public PurchaseRequisitionRepository purchaseRequisitionRepository;

    @Override
    public void sendToDatabase(PurchaseRequisition purchaseRequisition) {
        purchaseRequisitionRepository.save(purchaseRequisition);
    }
}
