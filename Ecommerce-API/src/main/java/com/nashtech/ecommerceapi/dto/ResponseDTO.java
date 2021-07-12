package com.nashtech.ecommerceapi.dto;

public class ResponseDTO {
    private String errorCode;
    private Object data;
    private String successCode;

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccessCode(String successCode) {
        this.successCode = successCode;
    }

    public Object getData() {
        return data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getSuccessCode() {
        return successCode;
    }
}
