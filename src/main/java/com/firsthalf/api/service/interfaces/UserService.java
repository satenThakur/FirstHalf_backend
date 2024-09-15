package com.firsthalf.api.service.interfaces;
import com.firsthalf.api.common.ApiResponse;

public interface UserService {
    ApiResponse generateOTP(String phoneNumber);
    ApiResponse verifyOtp(String phoneNumber,String otp);
    ApiResponse getCourses();
    ApiResponse getTutors();
}
