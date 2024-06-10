package com.studi.joticketing.exception;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException() {
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
