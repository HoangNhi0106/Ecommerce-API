package com.nashtech.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class UserExistedException extends Exception {
    private static final long serialVersionUID = 4L;

    public UserExistedException(String message) {
        super(message);
    }
}
