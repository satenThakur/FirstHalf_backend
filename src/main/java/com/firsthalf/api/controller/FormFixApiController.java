package com.firsthalf.api.controller;

import com.firsthalf.api.common.ApiKeys;
import com.firsthalf.api.common.ApiResponse;
import com.firsthalf.api.payload.ResponseData;
import com.firsthalf.api.payload.OtpDto;
import com.firsthalf.api.service.OtpService;
import com.firsthalf.api.utils.Utility;
import com.firsthalf.entity.User;
import com.firsthalf.service.UserService;
import com.firsthalf.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FormFixApiController {
    @Autowired
    private UserService userService;
    @Autowired
    OtpService otpService;

    @GetMapping("/user/login/{phone}")
    public ResponseEntity<ApiResponse> loginUser(@PathVariable String phone){
            ResponseData response = userService.getUserDetails(phone);
            return new ResponseEntity<>(Utility.getApiResponse(response, ApiKeys.RESPONSEDATA), HttpStatus.OK);
    }

    @GetMapping("delete_user/{id}")
    public ResponseEntity<ApiResponse> delete_user(@PathVariable Long id){
         userService.deleteUser(id);
        return new ResponseEntity<>(Utility.getApiResponse("User Deleted", ApiKeys.RESPONSEDATA), HttpStatus.OK);
    }
    @PostMapping("/generateOtp/{phone}")
    public  ResponseEntity<ApiResponse> generateOTP(@PathVariable String phone) {
        ResponseData response=otpService.generateOTP(phone);
        return new ResponseEntity<>(Utility.getApiResponse(response, ApiKeys.RESPONSEDATA), HttpStatus.OK);
    }


    @PostMapping("/validateOtp")
    public  ResponseEntity<ApiResponse> validateOtp(@RequestBody OtpDto otpDto) {
        ResponseData response=otpService.verifyOtp(otpDto.getPhone(),otpDto.getOtp());
        return new ResponseEntity<>(Utility.getApiResponse(response, ApiKeys.RESPONSEDATA), HttpStatus.OK);
    }
    @PostMapping("/checkUserExist/{phone}")
    public  ResponseEntity<ApiResponse> checkUserExist(@PathVariable String phone) {
        if(userService.checkIfUserAlreadyExist(phone)){
            return new ResponseEntity<>(Utility.getApiResponse(new ResponseData(Constants.SUCCESS,"User already exist with "+phone,null), ApiKeys.RESPONSEDATA), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Utility.getApiResponse(new ResponseData(Constants.FAILED,"User does not  exit with "+phone,null), ApiKeys.RESPONSEDATA), HttpStatus.OK);
        }

    }
    @PostMapping("/user/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody User user){
        if(!userService.checkIfUserAlreadyExist(user.getPhone())) {
            userService.createUser(user);
            ResponseData savedUser = userService.getUserDetails(user.getPhone());
            return new ResponseEntity<>(Utility.getApiResponse(savedUser, ApiKeys.RESPONSEDATA), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(Utility.getApiResponse(new ResponseData(Constants.FAILED,"User Already Exist for "+user.getPhone()+" Please SignIn or use another number to SignUp",null), ApiKeys.RESPONSEDATA), HttpStatus.OK);
        }
    }



    @GetMapping("/user/users")
    public ResponseEntity<ApiResponse> getAllUsers(){
       // return new ResponseEntity<>(studentsService.findAllStudents(), HttpStatus.OK);
        List<User>  users = userService.findAllUsers();
        return new ResponseEntity<>(Utility.getApiResponse(users, ApiKeys.RESPONSEDATA), HttpStatus.OK);
    }

}
