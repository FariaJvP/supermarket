package com.sm.supermarket.productandinventory.web.controller;

import com.sm.supermarket.productandinventory.usecases.CreatedProduct;
import com.sm.supermarket.productandinventory.web.dto.NewProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "${com.sm.supermarket.uri.productcontroller}")
public class NewProductController {

    @Autowired
    public CreatedProduct newProductCreation;

    @PostMapping
    public ResponseEntity<?> addProductInfo(@RequestBody @Valid NewProductRequest request){
            newProductCreation.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
