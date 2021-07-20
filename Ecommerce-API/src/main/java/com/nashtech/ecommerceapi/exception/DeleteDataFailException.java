package com.nashtech.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DeleteDataFailException extends Exception {
    public DeleteDataFailException(String message) {
        super(message);
    }
}
