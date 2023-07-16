package com.firsthalf.entity;

import lombok.Data;
import javax.persistence.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@Data
public class User {
    @Transient
    public static final String SEQUENCE_NAME="users_sequence";


    @Id
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
