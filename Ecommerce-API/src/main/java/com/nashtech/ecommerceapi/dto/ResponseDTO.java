package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
    private String errorCode;
    private Object data;
    private String successCode;
}
