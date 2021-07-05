package com.nashtech.ecommerceapi.exception;

public class ProductException extends RuntimeException{
    public ProductException(Integer id) {
        super("Cannot find product with id = " + id);
    }
}
