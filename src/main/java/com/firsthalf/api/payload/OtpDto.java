package com.firsthalf.api.payload;

import lombok.Data;

@Data
public class OtpDto {
    private String phone;
    private String otp;
}
