package com.firsthalf.controller;


import com.firsthalf.common.ApiKeys;
import com.firsthalf.common.ApiResponse;
import com.firsthalf.payload.LoginRequest;
import com.firsthalf.payload.UserDto;
import com.firsthalf.service.OtpService;
import com.firsthalf.service.interfaces.UserService;
import com.firsthalf.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    public UserController(UserService userService,OtpService otpService){
        this.userService=userService;
        this.otpService=otpService;
    }

    @PostMapping("/user/singup")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse>  loginUser(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(userService.userLogin(loginRequest), HttpStatus.OK);
    }
    @PostMapping("/user/forgetpassword")
    public ResponseEntity<ApiResponse>  forgetPassword(@RequestBody LoginRequest loginRequest){
        String phoneNumber=userService.forgetPassword(loginRequest);
        if(phoneNumber==null || phoneNumber.isEmpty()) {
            return new ResponseEntity<>(Utility.getApiResponse("User not exist with "+loginRequest.getEmail(),ApiKeys.USER), HttpStatus.OK);
        }
        String userName=phoneNumber;
       String msg= otpService.generateOTP(userName,phoneNumber);
        return new ResponseEntity<>(Utility.getApiResponse("verification code sent on "+phoneNumber,ApiKeys.USER), HttpStatus.OK);
    }


    @PostMapping("/user/resetpassword")
    public ResponseEntity<ApiResponse>  resetPassword(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(userService.resetPassword(loginRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getStudents(){
        return new ResponseEntity<>(userService. getUsers(), HttpStatus.OK);
    }


}
