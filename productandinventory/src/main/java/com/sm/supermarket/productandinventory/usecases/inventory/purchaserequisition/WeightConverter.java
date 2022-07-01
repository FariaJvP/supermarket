package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.product.PackageUnit;
import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class WeightConverter {

    private Map<PackageUnit, BigInteger> mapOfValues = new HashMap<PackageUnit, BigInteger>(){
        {
            put(PackageUnit.UNIT, BigInteger.valueOf(1));
            put(PackageUnit.MG, BigInteger.valueOf(1));
            put(PackageUnit.KG, BigInteger.valueOf(1000));
            put(PackageUnit.TON, BigInteger.valueOf(1000).pow(2));
            put(PackageUnit.ML, BigInteger.valueOf(1));
            put(PackageUnit.LT, BigInteger.valueOf(1000));
        }};

    public BigInteger convert(ProductToBeOrderedRequisition productToBeOrdered){
        if ((productToBeOrdered.getReferenceUnit() != null) && (!productToBeOrdered.getReferenceUnit().isEmpty())){
            if(!mapOfValues.keySet().toString().contains(productToBeOrdered.getReferenceUnit())) {
                throw new WrongConversionException("Could not convert the weight, please check for valid package units");
            }
            return productToBeOrdered.getQuantity().multiply(mapOfValues.get(PackageUnit.valueOf(productToBeOrdered.getReferenceUnit())));
        }
        throw new IllegalStateException("illegal state for package unit");
    }
}
