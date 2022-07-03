package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistory;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history.PurchaseRequisitionHistoryStatusUpdate;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.*;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.producttobeordered.CompositePurchaseRequisitionAndProduct;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.producttobeordered.EntityRepositoryForProductsToBeOrdered;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.producttobeordered.ProductToBeOrdered;
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

    private final WeightConverter weightConverter;

    @Autowired
    public CreatePurchaseRequisition(EntityRepositoryForProduct productRepository, EntityRepositoryForPurchaseRequisition purchaseRequisitionRepository,
                                     EntityRepositoryForProductsToBeOrdered productsToBeOrderedRepository,
                                     GeneratePurchaseRequisitionHistory generatePurchaseRequisitionHistory, WeightConverter weightConverter){
        this.productRepository = productRepository;
        this.purchaseRequisitionRepository = purchaseRequisitionRepository;
        this.productsToBeOrderedRepository = productsToBeOrderedRepository;
        this.generatePurchaseRequisitionHistory = generatePurchaseRequisitionHistory;
        this.listOfProductsToBeOrdered = new HashSet<>();
        this.weightConverter = new WeightConverter();
    }

    public void execute(PurchaseRequisitionForm requisition) {
        getPurchaseRequisitionForm(requisition);
        generateNewPurchaseRequisition();
        convertWeightOfProductToGranular();
        fillTheListOfProductsToBeOrderedWithWhatCameInRequisition();
        transactPurchaseRequisitionWithHistory();
    }

    private void getPurchaseRequisitionForm(PurchaseRequisitionForm requisition) {
        this.purchaseRequisitionForm = requisition;
    }

    private void convertWeightOfProductToGranular() {
        purchaseRequisitionForm.getListOfProductsToBeOrdered().forEach(product -> {
            product.convertWeighttoGranular(weightConverter.convert(product)) ;
        });
    }

    private void fillTheListOfProductsToBeOrderedWithWhatCameInRequisition() {
        purchaseRequisitionForm.getListOfProductsToBeOrdered().forEach(productInList ->{
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
