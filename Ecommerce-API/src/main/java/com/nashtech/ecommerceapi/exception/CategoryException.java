package com.nashtech.ecommerceapi.exception;

public class CategoryException extends RuntimeException {
    public CategoryException(long id) {
        super("Cannot fond category with id = " + id);
    }

    public CategoryException(String message) {
        super(message);
    }
}
