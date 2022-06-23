package com.sm.supermarket.productandinventory.infrastructure.springdatarepositories;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PurchaseRequisitionHistoryRepository extends JpaRepository<PurchaseRequisitionHistory, Long> {
}
