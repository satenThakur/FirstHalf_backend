package com.firsthalf.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    private Integer status;
    private Object data;
    private Object error;

    public ApiResponse() {
        this.status= HttpStatus.OK.value();
        this.data=null;
        this.error=null;
    }
}
