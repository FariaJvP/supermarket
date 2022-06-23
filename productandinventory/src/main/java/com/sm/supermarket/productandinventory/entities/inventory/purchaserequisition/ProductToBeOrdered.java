package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "tb_product_to_be_ordered")
public class ProductToBeOrdered {

    @EmbeddedId
    private CompositePurchaseRequisitionAndProduct productForPurchaseRequisition;

    private BigInteger quantity;

    @Deprecated
    public ProductToBeOrdered(){

    }

    public ProductToBeOrdered(CompositePurchaseRequisitionAndProduct productForPurchaseRequisition, BigInteger quantityToBeOrdered) {
        this.productForPurchaseRequisition = productForPurchaseRequisition;
        this.quantity = quantityToBeOrdered;
    }
}
