package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

import com.sm.supermarket.SupermarketApplication;
import com.sm.supermarket.productandinventory.builders.PurchaseRequisitionDataBuilder;
import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = SupermarketApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class purchaseRequisitionUsecaseTest {

    @Autowired
    private CreatePurchaseRequisition createPurchaseRequisition;

    @Test
    @DisplayName("should throws exception if the client send a negative value for a product to be ordered")
    public void test3() {

        PurchaseRequisitionForm purchaseRequisitionForm = new PurchaseRequisitionDataBuilder()
                .withProductToBeOrdered(1, BigInteger.valueOf(-1000000))
                .withProductToBeOrdered(2, BigInteger.valueOf(400))
                .withProductToBeOrdered(3, BigInteger.valueOf(100))
                .buildPurchaseRequisitionForm();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createPurchaseRequisition.execute(purchaseRequisitionForm));
    }

    @Test
    @DisplayName("should throws exception if the client send 0 as value for a product to be ordered")
    public void test4() {

        PurchaseRequisitionForm purchaseRequisitionForm = new PurchaseRequisitionDataBuilder()
                .withProductToBeOrdered(1, BigInteger.valueOf(0))
                .withProductToBeOrdered(2, BigInteger.valueOf(400))
                .withProductToBeOrdered(3, BigInteger.valueOf(100))
                .buildPurchaseRequisitionForm();

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> createPurchaseRequisition.execute(purchaseRequisitionForm));
    }

    @Test
    @DisplayName("should throws exception if the client send an invalid product id")
    public void test5() {

        PurchaseRequisitionForm purchaseRequisitionForm = new PurchaseRequisitionDataBuilder()
                .withProductToBeOrdered(55, BigInteger.valueOf(1000000))
                .withProductToBeOrdered(2, BigInteger.valueOf(400))
                .withProductToBeOrdered(3, BigInteger.valueOf(100))
                .buildPurchaseRequisitionForm();

        Assertions.assertThrows(ProductNotFoundException.class, () -> createPurchaseRequisition.execute(purchaseRequisitionForm));
    }
}
