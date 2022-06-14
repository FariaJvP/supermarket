package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.brand;

public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(String message){
        super(message);
    }
}
