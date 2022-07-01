package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.entities.product.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@PropertySource("classpath:messages.properties")
public class ProductValidator implements ConstraintValidator<ValidProduct, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${product}")
    private String message;

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext context) {
        if (Objects.isNull(productId)){
            throw new IllegalStateException(message);
        }
        Product product = entityManager.find(Product.class, productId);
        return product != null;
    }
}
