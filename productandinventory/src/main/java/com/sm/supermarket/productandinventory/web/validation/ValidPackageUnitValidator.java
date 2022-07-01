package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.entities.product.PackageUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@PropertySource("classpath:messages.properties")
public class ValidPackageUnitValidator implements ConstraintValidator<ValidPackageUnit, String> {

    private final PackageUnit[] values = PackageUnit.values();
    private final ArrayList<PackageUnit> list = new ArrayList<>(Arrays.asList(values));

    @Value("${packageunit}")
    private String errorMessage;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value) || value.isEmpty()){
            throw new IllegalStateException(errorMessage);
        }
        try {
            return list.contains(PackageUnit.valueOf(value));
        }catch (IllegalArgumentException exception){
            return false;
        }
    }
}
