package com.example.doctorbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoctorBeApplication {

//    Java Needs a dedicated place to "start" the program
//    It does not just start at the top of a file
//    Java is a compiled language
//    Starts at main, which needs to be static, public, and independent of any objects
    public static void main(String[] args) {
        SpringApplication.run(DoctorBeApplication.class, args);
    }

}
