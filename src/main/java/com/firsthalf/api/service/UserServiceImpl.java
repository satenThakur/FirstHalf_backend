package com.firsthalf.api.service;

import com.firsthalf.api.common.ApiResponse;
import com.firsthalf.api.service.interfaces.UserService;
import com.firsthalf.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository=userRepository;
        this.mapper=mapper;

    }


    @Override
    public ApiResponse generateOTP(String phoneNumber) {

        return null;
    }

    @Override
    public ApiResponse verifyOtp(String phoneNumber, String otp) {
        return null;
    }

    @Override
    public ApiResponse getCourses() {
        return null;
    }

    @Override
    public ApiResponse getTutors() {
        return null;
    }
}
