package com.sm.supermarket.productandinventory.infrastructure.springdatarepositories;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PurchaseRequisitionRepository extends JpaRepository<PurchaseRequisition, Long> {
}
