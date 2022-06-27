package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.*;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistoryStatusUpdate;
import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreatePurchaseRequisition {

    private final EntityRepositoryForProduct productRepository;

    private final EntityRepositoryForPurchaseRequisition purchaseRequisitionRepository;

    private final EntityRepositoryForProductsToBeOrdered productsToBeOrderedRepository;

    private final GeneratePurchaseRequisitionHistory generatePurchaseRequisitionHistory;

    private PurchaseRequisition purchaseRequisition;

    private final Set<ProductToBeOrdered> listOfProductsToBeOrdered;

    private PurchaseRequisitionForm purchaseRequisitionForm;

    @Autowired
    public CreatePurchaseRequisition(EntityRepositoryForProduct productRepository, EntityRepositoryForPurchaseRequisition purchaseRequisitionRepository,
                                     EntityRepositoryForProductsToBeOrdered productsToBeOrderedRepository,
                                     GeneratePurchaseRequisitionHistory generatePurchaseRequisitionHistory){
        this.productRepository = productRepository;
        this.purchaseRequisitionRepository = purchaseRequisitionRepository;
        this.productsToBeOrderedRepository = productsToBeOrderedRepository;
        this.generatePurchaseRequisitionHistory = generatePurchaseRequisitionHistory;
        this.listOfProductsToBeOrdered = new HashSet<>();
    }

    public void execute(PurchaseRequisitionForm requisition) {
        getPurchaseRequisitionForm(requisition);
        generateNewPurchaseRequisition();
        fillTheListOfProductsToBeOrderedWithWhatCameInRequisition(requisition);
        transactPurchaseRequisitionWithHistory();
    }

    private void getPurchaseRequisitionForm(PurchaseRequisitionForm requisition) {
        this.purchaseRequisitionForm = requisition;
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

    private void transactPurchaseRequisitionWithHistory() {
        sendPurchaseRequisitionToDatabase();
        sentoProductsToBeOrderedToDatabase();
        generateHistory();
    }

    private void sentoProductsToBeOrderedToDatabase() {
        this.productsToBeOrderedRepository.sendToDatabase(listOfProductsToBeOrdered);
    }

    private void sendPurchaseRequisitionToDatabase() {
        purchaseRequisitionRepository.sendToDatabase(purchaseRequisition);
    }

    private void generateHistory() {
        generatePurchaseRequisitionHistory.execute(new PurchaseRequisitionHistory(purchaseRequisition,
                PurchaseRequisitionHistoryStatusUpdate.PENDING, purchaseRequisitionForm.getDateTime()));
    }
}
