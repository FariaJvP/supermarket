package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.brand.BrandNotFoundException;
import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.product.ProductNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForBrandNotFound(BrandNotFoundException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.NOT_FOUND.value(), "brand not found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForProductNotFound(ProductNotFoundException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.NOT_FOUND.value(), "product not found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForIllegalState(IllegalStateException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.BAD_REQUEST.value(), "illegal state", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForCheckConstraintViolationThrewByDatabase(SQLException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.BAD_REQUEST.value(), "database check constraint violation", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForConstraintViolationThrewByBeanValidation(ConstraintViolationException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.BAD_REQUEST.value(), "database constraint violation", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForConstraintDataIntegrityViolation(DataIntegrityViolationException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.BAD_REQUEST.value(), "database data integrity violation", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForIllegalArgument(IllegalArgumentException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.BAD_REQUEST.value(), "illegal argument", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ResponseToWrongDataSentInForm> handlerForMethodArgumentNotValid(MethodArgumentNotValidException exception){
        List<ResponseToWrongDataSentInForm> responseList = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> {
            ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(), HttpStatus.BAD_REQUEST.value(), "method argument not valid", error.getDefaultMessage());
            responseList.add(response);
        });
        return responseList;
    }
}
