package com.optmap.optmap.errorHandling;

public class InvalidRouteRequestException extends RuntimeException{

    public InvalidRouteRequestException(String message) {
        super(message);
    }
}
