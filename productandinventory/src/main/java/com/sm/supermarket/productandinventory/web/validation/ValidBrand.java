package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.entities.brand.Brand;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { BrandValidator.class })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBrand {

    String message() default "{brand.messagefor.notfound}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    Class<Brand> domainClass();
    String fieldName();

}
