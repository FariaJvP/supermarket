package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition;

import javax.persistence.*;

@Entity
@Table(name = "tb_purchase_requisition")
public class PurchaseRequisition {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "process_status", nullable = false)
    private PurchaseRequisitionProcessStatus status;

    @Deprecated
    public PurchaseRequisition(){

    }

    public PurchaseRequisition(PurchaseRequisitionProcessStatus status) {
        this.status = status;
    }
}
