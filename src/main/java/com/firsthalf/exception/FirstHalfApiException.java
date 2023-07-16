package com.firsthalf.exception;

import org.springframework.http.HttpStatus;

public class FirstHalfApiException extends RuntimeException {
    private HttpStatus status;
    private String message;
    public FirstHalfApiException(HttpStatus status,String message){
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
