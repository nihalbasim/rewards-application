package com.fetch.rewards.exception;

public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(String id) {
        super("No receipt found for ID: " + id);
    }
}
