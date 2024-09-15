package com.firsthalf.api.payload;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String weight;
    private String height;
    private String token;
}
