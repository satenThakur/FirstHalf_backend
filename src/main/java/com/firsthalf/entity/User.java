package com.firsthalf.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",length = 250,nullable = false)
    private String name;

    @Column(name = "phone",length = 250,nullable = false)
    private String phone;
    @Column(name = "email",length = 250,nullable = false)
    private String email;

    @Column(name = "weight",length = 40,nullable = false)
    private String weight;

    @Column(name = "height",length = 40,nullable = false)
    private String height;


    public User(String name, String phone, String email, String weight, String height) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.weight = weight;
        this.height = height;
    }
}
