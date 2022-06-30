package com.sm.supermarket.productandinventory.web.dto;

import com.sm.supermarket.productandinventory.entities.brand.Brand;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductValidator implements ConstraintValidator<ValidProduct, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private String message;

    @Override
    public void initialize(ValidProduct constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext context) {
        Brand brand = entityManager.find(Brand.class, productId);
        return brand != null;
    }
}
