package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.EntityRepositoryForProductsToBeOrdered;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.ProductToBeOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductToBeOrderedRepositoryUsingSpringJpa implements EntityRepositoryForProductsToBeOrdered {

    @Autowired
    private ProductToBeOrderedRepository repository;

    @Override
    public void sendToDatabase(Set<ProductToBeOrdered> listOfProductsToBeOrdered) {
        repository.saveAll(listOfProductsToBeOrdered);
    }
}
