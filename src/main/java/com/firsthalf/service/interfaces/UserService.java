package com.firsthalf.service.interfaces;

import com.firsthalf.common.ApiResponse;
import com.firsthalf.payload.LoginRequest;
import com.firsthalf.payload.UserDto;

public interface UserService {
    ApiResponse createUser(UserDto userDto);
    ApiResponse userLogin(LoginRequest loginRequest);

    String forgetPassword(LoginRequest loginRequest);
    ApiResponse resetPassword(LoginRequest loginRequest);
    ApiResponse getUsers();
    ApiResponse updateUser(UserDto userDto);
    void deleteUser(Long id);
}
