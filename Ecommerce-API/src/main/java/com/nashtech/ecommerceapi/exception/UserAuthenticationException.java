package com.nashtech.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserAuthenticationException extends Exception {
    private static final long serialVersionUID = 4L;

    public UserAuthenticationException(String message) {
        super(message);
    }
}
