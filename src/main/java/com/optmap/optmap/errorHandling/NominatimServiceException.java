package com.optmap.optmap.errorHandling;

public class NominatimServiceException extends RuntimeException {
    public NominatimServiceException(String message) {
        super(message);
    }
}
