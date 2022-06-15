package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.brand.BrandNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForBrandNotFound(BrandNotFoundException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.NOT_FOUND.value(), "brand not found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
