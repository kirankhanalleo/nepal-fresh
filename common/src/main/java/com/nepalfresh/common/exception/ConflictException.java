package com.nepalfresh.common.exception;

public class ConflictException extends RuntimeException {

    public ConflictException() {
        super();
    }

    public ConflictException(final String message) {
        super(message);
    }

}