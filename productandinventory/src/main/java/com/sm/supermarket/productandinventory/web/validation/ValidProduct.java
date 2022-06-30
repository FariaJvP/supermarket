package com.sm.supermarket.productandinventory.web.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { ProductValidator.class })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProduct {

    String message() default "{product.notfound}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
