package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.util.Set;

@Component
public class UniqueTransactionForPurchaseRequisitionWithHistory {

    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    public void setAtomicTransaction(PurchaseRequisition purchaseRequisition,
                                  Set<ProductToBeOrdered> listOfProductsToBeOrdered,
                                  PurchaseRequisitionHistory purchaseRequisitionHistory){

        try {
            entityManager.persist(purchaseRequisition);
            listOfProductsToBeOrdered.forEach(productToBeOrdered -> {
                entityManager.persist(productToBeOrdered);
            });
            entityManager.persist(purchaseRequisitionHistory);
        }catch (TransactionalException exception){
            entityManager.getTransaction().rollback();
            exception.getMessage();
        }

    }

}
