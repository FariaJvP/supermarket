package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.producttobeordered;

import java.util.Set;

public interface EntityRepositoryForProductsToBeOrdered {

    void sendToDatabase(Set<ProductToBeOrdered> listOfProductsToBeOrdered);
}
