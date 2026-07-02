package com.optmap.optmap.errorHandling;

public class InvalidSearchRequestException extends RuntimeException {
    public InvalidSearchRequestException(String message) {
        super(message);
    }
}
