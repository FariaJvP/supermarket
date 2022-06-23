package com.sm.supermarket.productandinventory.web.controller;

import com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition.CreatePurchaseRequisition;
import com.sm.supermarket.productandinventory.web.dto.PurchaseRequisitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${com.sm.supermarket.uri.purchaserequisition.new}")
public class NewPurchaseRequisitionController {

    private final CreatePurchaseRequisition newPurchaseRequisition;

    @Autowired
    public NewPurchaseRequisitionController(CreatePurchaseRequisition newPurchaseRequisition){
        this.newPurchaseRequisition = newPurchaseRequisition;
    }

    @PostMapping
    public ResponseEntity<?> sendNewPurchaseRequisition(@RequestBody @Valid PurchaseRequisitionRequest request){
        newPurchaseRequisition.execute(request.convertIntoPurchaseRequisitionForm());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
