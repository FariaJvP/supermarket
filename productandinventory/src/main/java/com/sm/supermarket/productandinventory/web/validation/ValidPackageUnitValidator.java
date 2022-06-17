package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.entities.product.PackageUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;

public class ValidPackageUnitValidator implements ConstraintValidator<ValidPackageUnit, String> {

    private final PackageUnit[] values = PackageUnit.values();
    private final ArrayList<PackageUnit> list = new ArrayList<>(Arrays.asList(values));

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return list.contains(PackageUnit.valueOf(value));
        }catch (IllegalArgumentException exception){
            return false;
        }
    }
}
