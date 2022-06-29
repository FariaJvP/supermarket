package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.EntityRepositoryForProductsToBeOrdered;
import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.ProductToBeOrdered;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.ProductToBeOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Set;

@Component
public class ProductToBeOrderedRepositoryUsingSpringJpa implements EntityRepositoryForProductsToBeOrdered {

    @Autowired
    private ProductToBeOrderedRepository repository;

    @Override
    public void sendToDatabase(Set<ProductToBeOrdered> listOfProductsToBeOrdered) {
        try{
            repository.saveAll(listOfProductsToBeOrdered);
        }catch (JpaSystemException exception){
            Throwable rootCause = NestedExceptionUtils.getRootCause(exception);

            if (rootCause instanceof SQLException && rootCause.getMessage().startsWith("Check constraint")){
                throw new DataIntegrityViolationException(rootCause.getMessage());
            }
        }
    }
}
