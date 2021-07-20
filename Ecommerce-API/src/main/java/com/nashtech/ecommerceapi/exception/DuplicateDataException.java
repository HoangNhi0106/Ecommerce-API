package com.nashtech.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DuplicateDataException extends Exception {
    private static final long serialVersionUID = 4L;

    public DuplicateDataException(String message) {
        super(message);
    }
}
