package ru.ufanet.coffeeshoporderingsystem.exception;

public class OrderStatusException extends RuntimeException {

    public OrderStatusException(String message) {
        super(message);
    }
}
