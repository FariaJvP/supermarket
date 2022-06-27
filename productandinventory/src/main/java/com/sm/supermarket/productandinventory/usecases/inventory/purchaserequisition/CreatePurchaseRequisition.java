package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.CompositePurchaseRequisitionAndProduct;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisitionProcessStatus;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistoryStatusUpdate;
import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class CreatePurchaseRequisition {

    private final EntityRepositoryForProduct productRepository;

    private PurchaseRequisitionHistory purchaseRequisitionHistory;

    private PurchaseRequisition purchaseRequisition;

    private final Set<ProductToBeOrdered> listOfProductsToBeOrdered;

    private final UniqueTransactionForPurchaseRequisitionWithHistory uniqueTransaction;

    @Autowired
    public CreatePurchaseRequisition(EntityRepositoryForProduct productRepository, UniqueTransactionForPurchaseRequisitionWithHistory uniqueTransaction){
        this.productRepository = productRepository;
        this.uniqueTransaction = uniqueTransaction;
        this.listOfProductsToBeOrdered = new HashSet<>();
    }

    public void execute(PurchaseRequisitionForm requisition) {
        generateNewPurchaseRequisition();
        fillTheListOfProductsToBeOrderedWithWhatCameInRequisition(requisition);
        generateHistory(requisition.getDateTime());
        transactPurchaseRequisitionWithHistory();
    }

    private void fillTheListOfProductsToBeOrderedWithWhatCameInRequisition(PurchaseRequisitionForm requisition) {
        requisition.getListOfProductsToBeOrdered().forEach(productInList ->{
            Product product = productRepository.findById(productInList.getProductId());
            listOfProductsToBeOrdered.add(new ProductToBeOrdered(new CompositePurchaseRequisitionAndProduct(purchaseRequisition, product), productInList.getQuantity()));
        });
    }

    private void generateNewPurchaseRequisition(){
        this.purchaseRequisition = new PurchaseRequisition(PurchaseRequisitionProcessStatus.WAITING_HISTORY_STATUS_UPDATE);
    }

    public void transactPurchaseRequisitionWithHistory() {
        uniqueTransaction.setAtomicTransaction(purchaseRequisition, listOfProductsToBeOrdered, purchaseRequisitionHistory);
    }

    private void generateHistory(LocalDateTime datetime) {
        this.purchaseRequisitionHistory = new PurchaseRequisitionHistory(purchaseRequisition,
                PurchaseRequisitionHistoryStatusUpdate.PENDING, datetime);
    }
}
