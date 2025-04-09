package com.fetch.rewards.exception;

public class InvalidReceiptException extends RuntimeException{
    public InvalidReceiptException(Exception e){
        super("Unable to process receipt: " + e.getMessage());
    }
}
