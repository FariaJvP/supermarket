package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
