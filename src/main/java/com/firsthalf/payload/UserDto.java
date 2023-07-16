package com.firsthalf.payload;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String gender;
    private String batchId;
    private String courseId;
    private String profile_pic;
    private String password;
    private int type;
    private String token;
}
