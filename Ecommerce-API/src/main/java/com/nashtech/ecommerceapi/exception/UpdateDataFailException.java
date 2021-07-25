package com.nashtech.ecommerceapi.exception;

import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UpdateDataFailException extends Exception {
    public UpdateDataFailException(String message){
        super(message);
    }
}
