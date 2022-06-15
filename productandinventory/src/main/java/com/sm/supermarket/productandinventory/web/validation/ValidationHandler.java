package com.sm.supermarket.productandinventory.web.validation;

import com.sm.supermarket.productandinventory.infrastructure.domainentitiesinterfacerepositories.brand.BrandNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.time.Instant;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseToWrongDataSentInForm> handlerForBrandNotFound(BrandNotFoundException exception){
        ResponseToWrongDataSentInForm response = new ResponseToWrongDataSentInForm(Instant.now(),
                HttpStatus.NOT_FOUND.value(), "brand not found", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
}
