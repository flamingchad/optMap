package com.optmap.optmap.errorHandling;

public class OsrmServiceException extends RuntimeException {
    public OsrmServiceException (String message) {
        super(message);
    }
}
