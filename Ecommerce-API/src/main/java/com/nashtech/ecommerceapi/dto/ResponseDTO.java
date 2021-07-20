package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String errorCode;
    private Object data;
    private String successCode;

    public ResponseDTO() {
        this.errorCode = null;
        this.data = null;
        this.successCode = null;
    }

    public ResponseDTO(String errorCode, Object data, String successCode) {
        this.errorCode = errorCode;
        this.data = data;
        this.successCode = successCode;
    }
}
