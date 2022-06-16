package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.entities.brand.Brand;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BrandValidator implements ConstraintValidator<ValidBrand, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    private String domainAttribute;
    private Class<?> classWhichCalledThisValidator;
    private String message;

    @Override
    public void initialize(ValidBrand constraintAnnotation) {
        this.domainAttribute = constraintAnnotation.fieldName();
        this.classWhichCalledThisValidator = constraintAnnotation.domainClass();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Long brandId, ConstraintValidatorContext context) {
        Brand brand = entityManager.find(Brand.class, brandId);
        return brand != null;
    }
}
