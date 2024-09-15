package com.firsthalf.api.payload;

public class ResponseData {
  public   int code;
    public  Object data;
    public  String message;

    public ResponseData(int code, String message, Object data) {
        this.code = code;
        this.message=message;
        this.data = data;

    }
}
