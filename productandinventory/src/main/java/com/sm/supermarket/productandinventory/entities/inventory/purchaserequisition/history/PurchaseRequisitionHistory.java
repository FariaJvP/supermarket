package com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.history;

import com.sm.supermarket.productandinventory.entities.inventory.purchaserequisition.PurchaseRequisition;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_purchase_requisition_history")
public class PurchaseRequisitionHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "purchase_requisition_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "history_purchase_requisition_id"))
    private PurchaseRequisition purchaseRequisition;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_update", nullable = false)
    private PurchaseRequisitionHistoryStatusUpdate status;

    @Column(name = "history_datetime")
    private LocalDateTime dateTime;

    @Deprecated
    public PurchaseRequisitionHistory(){

    }

    public PurchaseRequisitionHistory(PurchaseRequisition purchaseRequisition, PurchaseRequisitionHistoryStatusUpdate status, LocalDateTime dateTime) {
        this.purchaseRequisition = purchaseRequisition;
        this.status = status;
        this.dateTime = dateTime;
    }
}
