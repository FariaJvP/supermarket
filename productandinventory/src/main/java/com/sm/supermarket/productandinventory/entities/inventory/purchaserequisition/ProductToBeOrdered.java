package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.product.Product;

import java.math.BigInteger;

public class ProductToBeOrdered {

    private long id;

    private PurchaseRequisition purchaseRequisition;

    private Product product;

    private BigInteger quantity;

    public ProductToBeOrdered(Product product, BigInteger quantityToBeOrdered) {
        this.product = product;
        this.quantity = quantityToBeOrdered;
    }
}
