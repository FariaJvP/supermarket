package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.EntityRepositoryForPurchaseRequisition;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisitionProcessStatus;
import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import com.sm.supermarket.productandinventory.web.dto.PurchaseRequisitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CreatePurchaseRequisition {

    @Autowired
    public EntityRepositoryForProduct productRepository;

    @Autowired
    public EntityRepositoryForPurchaseRequisition purchaseRequisitionRepository;

    private PurchaseRequisition purchaseRequisition;

    private Set<ProductToBeOrdered> listOfProductsToBeOrdered;

    public void execute(List<PurchaseRequisitionRequest> requisition) {
        fillTheListOfProductsToBeOrderedWithWhatCameInRequisition(requisition);
        generateNewPurchaseRequisition();
        transactPurchaseRequisitionWithHistory();
    }

    private void fillTheListOfProductsToBeOrderedWithWhatCameInRequisition(List<PurchaseRequisitionRequest> requisition) {
        this.listOfProductsToBeOrdered = new HashSet<>();

        requisition.forEach(productInList ->{
            Product product = productRepository.findById(productInList.getProductId());
            listOfProductsToBeOrdered.add(new ProductToBeOrdered(product, productInList.getQuantityToBeOrdered()));
        });
    }

    private void generateNewPurchaseRequisition(){
        this.purchaseRequisition = new PurchaseRequisition(PurchaseRequisitionProcessStatus.WAITING_HISTORY_STATUS_UPDATE, listOfProductsToBeOrdered);
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
        //TODO -> WIP
    }
}
