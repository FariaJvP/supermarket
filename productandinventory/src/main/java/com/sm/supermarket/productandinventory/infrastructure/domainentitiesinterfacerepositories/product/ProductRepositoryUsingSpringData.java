package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product;

import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryUsingSpringData implements EntityRepositoryForProduct {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public void sendToDatabase(Product product) {
        productRepository.save(product);
    }
}
