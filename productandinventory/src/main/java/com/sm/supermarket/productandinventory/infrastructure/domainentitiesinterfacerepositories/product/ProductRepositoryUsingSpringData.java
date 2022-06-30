package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product;

import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
@PropertySource("classpath:messages.properties")
public class ProductRepositoryUsingSpringData implements EntityRepositoryForProduct {

    private final ProductRepository productRepository;

    private final String errorMessage;

    @Autowired
    public ProductRepositoryUsingSpringData(ProductRepository productRepository, @Value("${product.notfound.database}") String productMessageNotFoundDatabase) {
        this.productRepository = productRepository;
        this.errorMessage = productMessageNotFoundDatabase;
    }

    @Override
    public void sendToDatabase(Product product) {
        try{
            productRepository.save(product);
        }catch (JpaSystemException exception){
            Throwable rootCause = NestedExceptionUtils.getRootCause(exception);
            if (rootCause instanceof SQLException && rootCause.getMessage().startsWith("Check constraint")){
                throw new DataIntegrityViolationException(rootCause.getMessage());
            }
        }
    }

    @Override
    public Product findById(long productId) {
        Optional<Product> productSearch = productRepository.findById(productId);
        if (!productSearch.isPresent()){
            throw new ProductNotFoundException(errorMessage + productId);
        }else {
            return productSearch.get();
        }
    }
}