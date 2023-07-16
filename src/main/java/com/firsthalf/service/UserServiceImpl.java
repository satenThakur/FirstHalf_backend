package com.firsthalf.service;

import com.firsthalf.common.ApiKeys;
import com.firsthalf.common.ApiResponse;
import com.firsthalf.entity.User;
import com.firsthalf.payload.LoginRequest;
import com.firsthalf.payload.UserDto;
import com.firsthalf.repository.UserRepository;
import com.firsthalf.service.interfaces.UserService;
import com.firsthalf.utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper mapper;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository=userRepository;
        this.mapper=mapper;
    }

    @Override
    public ApiResponse createUser(UserDto userDto) {

        if (userRepository.findByUserEmail(userDto.getEmail())!=null) {
            return  Utility.getApiResponse("Error: Email is already in use!", ApiKeys.USER);
        }

        //convert Data to entity
        User newUser=mapper.map(userDto, User.class);
        newUser.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        userRepository.save(newUser);

        //convert Entity to Data
        UserDto newUserResponse=mapper.map(newUser, UserDto.class);
        return  Utility.getApiResponse(newUserResponse, ApiKeys.USER);
    }

    @Override
    public ApiResponse userLogin(LoginRequest loginRequest) {
        if (userRepository.findByUserEmail(loginRequest.getEmail())!=null) {
            User user = userRepository.findByUserEmail(loginRequest.getEmail());
            if(user.getPassword().equalsIgnoreCase(loginRequest.getPassword())){
                return  Utility.getApiResponse(user, ApiKeys.USER);
            }else{
                return  Utility.getApiResponse("Password is incorrect", ApiKeys.USER);
            }
        }

        return  Utility.getApiResponse("No User Found with "+loginRequest.getEmail(), ApiKeys.USER);
    }

    @Override
    public String forgetPassword(LoginRequest loginRequest) {
        if (userRepository.findByUserEmail(loginRequest.getEmail())!=null) {
            User user = userRepository.findByUserEmail(loginRequest.getEmail());
            return user.getPhoneNumber();
        }else{
            return null;
        }
    }

    @Override
    public ApiResponse resetPassword(LoginRequest loginRequest) {
        if (userRepository.findByUserEmail(loginRequest.getEmail())!=null) {
            User user = userRepository.findByUserEmail(loginRequest.getEmail());
            user.setPassword(loginRequest.getPassword());
            userRepository.save(user);
            return Utility.getApiResponse("Password Reset Successfully",ApiKeys.MESSAGE);
        }else{
            return Utility.getApiResponse("something went wrong",ApiKeys.MESSAGE);
        }
    }


    @Override
    public ApiResponse getUsers() {
        List<UserDto> studentDtoList=new ArrayList<>();
        List<User> students=userRepository.findAll();
        for(User std : students)
        {
            UserDto newStudent=mapper.map(std, UserDto.class);
            studentDtoList.add(newStudent);
        }
        return Utility.getApiResponse(studentDtoList,ApiKeys.USERS);
    }

    @Override
    public ApiResponse updateUser(UserDto studentDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
