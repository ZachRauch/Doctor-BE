package com.example.doctorbe.service;

import com.example.doctorbe.models.AppUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Services in spring:
//  dedicated to handling logic
//  available to be injected anywhere
//  meant to be as pure Java as possible
@Service
public class UserService {

    private Long nextUserId = 0L;

    private final ArrayList<AppUser> users = new ArrayList<>();

    public AppUser getByUsernameAndPassword(String username, String password) {
        for (AppUser user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void register( AppUser user) throws Exception {
        for (AppUser existingUser : users) {
            if (user.username.equals(existingUser.username)) {
                throw new Exception();
            }
        }
        user.id = nextUserId++;
        users.add(user);
    }
}
