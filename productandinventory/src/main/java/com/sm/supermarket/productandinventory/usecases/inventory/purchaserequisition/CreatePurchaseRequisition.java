package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.*;
import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.util.HashSet;
import java.util.Set;

@Component
public class CreatePurchaseRequisition {

    private final EntityRepositoryForProduct productRepository;

    private final EntityRepositoryForPurchaseRequisition purchaseRequisitionRepository;

    private final GeneratePurchaseRequisitionHistory purchaseRequisitionHistory;

    private PurchaseRequisition purchaseRequisition;

    private Set<ProductToBeOrdered> listOfProductsToBeOrdered;

    private final PurchaseRequisitionForm requisition;

    @Autowired
    public CreatePurchaseRequisition(EntityRepositoryForProduct productRepository, EntityRepositoryForPurchaseRequisition purchaseRequisitionRepository,
                                     GeneratePurchaseRequisitionHistory purchaseRequisitionHistory , PurchaseRequisitionForm purchaseRequisitionForm){
        this.productRepository = productRepository;
        this.purchaseRequisitionRepository = purchaseRequisitionRepository;
        this.purchaseRequisitionHistory = purchaseRequisitionHistory;
        this.requisition = purchaseRequisitionForm;
    }

    public void execute(PurchaseRequisitionForm requisition) {
        fillTheListOfProductsToBeOrderedWithWhatCameInRequisition();
        generateNewPurchaseRequisition();
        transactPurchaseRequisitionWithHistory();
    }

    private void fillTheListOfProductsToBeOrderedWithWhatCameInRequisition() {
        this.listOfProductsToBeOrdered = new HashSet<>();

        this.requisition.getListOfProductsToBeOrdered().forEach(productInList ->{
            Product product = productRepository.findById(productInList.getProductId());
            listOfProductsToBeOrdered.add(new ProductToBeOrdered(new CompositePurchaseRequisitionAndProduct(purchaseRequisition, product), productInList.getQuantity()));
        });
    }

    private void generateNewPurchaseRequisition(){
        this.purchaseRequisition = new PurchaseRequisition(PurchaseRequisitionProcessStatus.WAITING_HISTORY_STATUS_UPDATE);
    }

    @Transactional
    private void transactPurchaseRequisitionWithHistory() {
        try {
            sendPurchaseRequisitionToDatabase();
            generateHistory();
        }catch (TransactionalException exception){
            exception.getMessage();
        }
    }

    private void sendPurchaseRequisitionToDatabase() {
        purchaseRequisitionRepository.sendToDatabase(purchaseRequisition);
    }

    private void generateHistory() {
        purchaseRequisitionHistory.execute(purchaseRequisition, requisition.getDateTime());
    }
}
