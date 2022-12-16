package com.example.doctorbe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String username;
    public String password;
    public boolean doctor;

    public AppUser() {}

    public AppUser(Long id, String username, String password, boolean doctor) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.doctor = doctor;
    }

}
