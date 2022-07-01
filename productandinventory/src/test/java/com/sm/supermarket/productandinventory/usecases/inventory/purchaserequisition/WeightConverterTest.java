package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.web.dto.ProductToBeOrderedRequisition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;

@ActiveProfiles("test")
public class WeightConverterTest {

    WeightConverter weightConverter;

    ProductToBeOrderedRequisition productToBeOrdered;

    @BeforeEach
    public void before(){
        weightConverter = new WeightConverter();
    }

    @Test
    @DisplayName("should transform a ton weight requisition into solid granular for product")
    public void test1(){
        productToBeOrdered = new ProductToBeOrderedRequisition(1,"TON", new BigInteger("1"));

        BigInteger result = weightConverter.convert(productToBeOrdered);

        Assertions.assertEquals(BigInteger.valueOf(1000000), result);
    }

    @Test
    @DisplayName("should not accept lt if product has mg, kg, ton or unit as package unit")
    public void test2(){
        productToBeOrdered = new ProductToBeOrderedRequisition(1,"KG", new BigInteger("1000"));

        BigInteger result = weightConverter.convert(productToBeOrdered);

        Assertions.assertEquals(BigInteger.valueOf(1000000), result);
    }

    @Test
    @DisplayName("should transform a LT weight requisition into liquid granular for product")
    public void test3(){
        productToBeOrdered = new ProductToBeOrderedRequisition(1,"LT", new BigInteger("1000"));

        BigInteger result = weightConverter.convert(productToBeOrdered);

        Assertions.assertEquals(BigInteger.valueOf(1000000), result);
    }

    @Test
    @DisplayName("should trow an exception if the client send a wrong value for package unit")
    public void test4(){
        productToBeOrdered = new ProductToBeOrderedRequisition(1,"WRONG", new BigInteger("1"));

        Assertions.assertThrows(WrongConversionException.class, () -> weightConverter.convert(productToBeOrdered));
    }

    @Test
    @DisplayName("should trow an exception if the client send a null value for package unit")
    public void test5(){
        productToBeOrdered = new ProductToBeOrderedRequisition(1,null, new BigInteger("1"));

        Assertions.assertThrows(IllegalStateException.class, () -> weightConverter.convert(productToBeOrdered));
    }

    @Test
    @DisplayName("should trow an exception if the client send a empty value for package unit")
    public void test6(){
        productToBeOrdered = new ProductToBeOrderedRequisition(1,"", new BigInteger("1"));

        Assertions.assertThrows(IllegalStateException.class, () -> weightConverter.convert(productToBeOrdered));
    }

}