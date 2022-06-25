package com.sm.supermarket.productandinventory.infrastructure.springdatarepositories;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.CompositePurchaseRequisitionAndProduct;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductToBeOrderedRepository extends JpaRepository<ProductToBeOrdered, CompositePurchaseRequisitionAndProduct> {
}
