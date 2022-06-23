package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition;

import com.sm.supermarket.productandinventory.entities.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositePurchaseRequisitionAndProduct implements Serializable {

    @OneToOne
    @JoinColumn(name = "purchase_requisition_id", referencedColumnName = "id", foreignKey = @ForeignKey  (name = "purchase_requisition_id"))
    private PurchaseRequisition purchaseRequisition;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Deprecated
    public CompositePurchaseRequisitionAndProduct(){

    }

    public CompositePurchaseRequisitionAndProduct(PurchaseRequisition purchaseRequisition, Product product) {
        this.purchaseRequisition = purchaseRequisition;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositePurchaseRequisitionAndProduct that = (CompositePurchaseRequisitionAndProduct) o;
        return Objects.equals(purchaseRequisition, that.purchaseRequisition) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseRequisition, product);
    }
}
