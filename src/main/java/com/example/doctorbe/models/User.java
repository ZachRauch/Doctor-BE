package com.example.doctorbe.models;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class User {
    public Long id;
    public String username;
    public String password;
    public boolean doctor;

    public User() {}

    public User(Long id, String username, String password, boolean doctor) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.doctor = doctor;
    }

}
