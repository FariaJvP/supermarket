package com.sm.supermarket.productandinventory.entities.product;

public interface EntityRepositoryForProduct {

    void sendToDatabase(Product product);

    Product findById(long productId);
}
