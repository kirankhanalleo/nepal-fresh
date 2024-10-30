package com.nepalfresh.common.exception;

import lombok.Getter;

@Getter
public class ForbiddenRequestException extends RuntimeException {
    public ForbiddenRequestException(String message) {
        super(message);
    }
}
