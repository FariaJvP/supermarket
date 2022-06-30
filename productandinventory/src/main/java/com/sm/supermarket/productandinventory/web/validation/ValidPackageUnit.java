package com.sm.supermarket.productandinventory.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { ValidPackageUnitValidator.class })
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPackageUnit {

    String message() default "{packageunit.notvalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
