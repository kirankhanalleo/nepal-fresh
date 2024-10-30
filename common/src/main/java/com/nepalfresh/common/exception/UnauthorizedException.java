package com.nepalfresh.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public class UnauthorizedException {
    private final String message;
    private final HttpStatus httpStatus;
    private final String code;
}
