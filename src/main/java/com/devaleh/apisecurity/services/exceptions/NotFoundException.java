package com.devaleh.apisecurity.services.exceptions;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(Object id) {
        super("Resource not found. Id: " + id);
    }
}