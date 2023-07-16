package com.firsthalf.controller;

import com.firsthalf.common.ApiKeys;
import com.firsthalf.common.ApiResponse;
import com.firsthalf.repository.UserRepository;
import com.firsthalf.service.OtpService;
import com.firsthalf.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OtpController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public OtpService otpService;

    @GetMapping("/generateOtp/{phone}")
    public  ResponseEntity<ApiResponse> generateOTP(@PathVariable String phone) throws MessagingException {
       // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ""+phone;//auth.getName();
        String response = otpService.generateOTP(username,phone);
        if(userRepository.existsByPhoneNumber(phone)!=null){
            return new ResponseEntity<>(Utility.getApiResponse("Existing user !!  OTP has been sent to "+phone, ApiKeys.MESSAGE), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Utility.getApiResponse(response, ApiKeys.MESSAGE), HttpStatus.OK);
        }

    }


    @GetMapping("/validateOtp/{phone}/{otp}")
    public  ResponseEntity<ApiResponse> validateOTP(@PathVariable String phone,@PathVariable int otp) throws MessagingException {
        String message="";
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username =phone;// auth.getName();
        //Validate the Otp
        if(otp >= 0) {
            int serverOtp = otpService.getOtp(username);
            if (serverOtp > 0) {
                if (otp == serverOtp) {
                    otpService.clearOTP(username);
                    message = "Entered Otp is valid";
                }

            }else{
                message = "Entered Otp is invalid or expired";
            }
        }else{
                message = "Entered Otp is invalid or expired";

        }

        return new ResponseEntity<>(Utility.getApiResponse(message, ApiKeys.MESSAGE), HttpStatus.OK);
    }


}
