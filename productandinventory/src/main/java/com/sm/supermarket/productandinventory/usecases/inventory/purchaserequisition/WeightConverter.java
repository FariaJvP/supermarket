package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.product.PackageUnit;
import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;

import java.math.BigInteger;

public class WeightConverter {

    public BigInteger convert(ProductToBeOrderedRequisition productToBeOrdered){
        if ((productToBeOrdered.getReferenceUnit() != null) && (!productToBeOrdered.getReferenceUnit().isEmpty())){
            if (productToBeOrdered.getReferenceUnit().equals(PackageUnit.UNIT.toString())){
                return productToBeOrdered.getQuantity();
            }
            if(productToBeOrdered.getReferenceUnit().equals(PackageUnit.TON.toString())) {
                return productToBeOrdered.getQuantity().multiply(BigInteger.valueOf(1000)).pow(2);
            }
            if(productToBeOrdered.getReferenceUnit().equals(PackageUnit.KG.toString())){
                return productToBeOrdered.getQuantity().multiply(BigInteger.valueOf(1000));
            }
            if(productToBeOrdered.getReferenceUnit().equals(PackageUnit.LT.toString())){
                return productToBeOrdered.getQuantity().multiply(BigInteger.valueOf(1000));
            }
        }
        else {
            throw new IllegalStateException("illegal state for package unit");
        }
        throw new WrongConversionException("Could not convert the weight, please check for valid package units");
    }

}
