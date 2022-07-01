package com.sm.supermarket.productandinventory.usecases.inventory.purchaserequisition;

public class WrongConversionException extends RuntimeException {
    public WrongConversionException(String message) {
        super(message);
    }
}
