package com.firsthalf.api.exception;

import org.springframework.http.HttpStatus;

public class FormFixApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
    public FormFixApiException(HttpStatus status, String message){
        super(message);
        this.status=status;
    }

    public HttpStatus getStatus(){
        return status;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
