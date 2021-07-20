package com.nashtech.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class ProductOutOfStockException extends Exception {
    private static final long serialVersionUID = 4L;

    public ProductOutOfStockException(String message) {
        super(message);
    }
}
