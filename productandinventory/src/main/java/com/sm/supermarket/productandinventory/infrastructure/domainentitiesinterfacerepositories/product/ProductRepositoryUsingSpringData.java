package com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product;

import com.sm.supermarket.productandinventory.entities.product.EntityRepositoryForProduct;
import com.sm.supermarket.productandinventory.entities.product.Product;
import com.sm.supermarket.productandinventory.infrastructure.springdatarepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryUsingSpringData implements EntityRepositoryForProduct {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public void sendToDatabase(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(long productId) {
        Optional<Product> productSearch = productRepository.findById(productId);
        if (!productSearch.isPresent()){
            throw new ProductNotFoundException("could not find a valid Brand in database with the id: id"  + productId);
        }else {
            return productSearch.get();
        }
    }
}
