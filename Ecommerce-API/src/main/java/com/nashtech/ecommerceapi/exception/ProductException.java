package com.nashtech.ecommerceapi.exception;

public class ProductException extends RuntimeException{
    public ProductException(long id) {
        super("Cannot find product with id = " + id);
    }
}
