package com.example.exception;

public class CustomerNotfoundException extends RuntimeException {
    public CustomerNotfoundException(Integer customerId){
        super("Customer is not found (CustomerId = " + customerId + ")");
    }
}
