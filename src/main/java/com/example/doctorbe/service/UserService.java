package com.example.doctorbe.service;

import com.example.doctorbe.models.AppUser;
import com.example.doctorbe.repositories.UserRepository;
import org.springframework.stereotype.Service;

// Services in spring:
//  dedicated to handling logic
//  available to be injected anywhere
//  meant to be as pure Java as possible
@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public AppUser getByUsernameAndPassword(String username, String password) {
        return repository.findAppUserByUsernameAndPassword(username, password).orElse(null); // replaces SQL statement
//          replaces
//        for (AppUser user : users) {
//            if (user.username.equals(username) && user.password.equals(password)) {
//                return user;
//            }
//        }
//        return null;
    }

    public void register( AppUser user) throws Exception {
        if (repository.findAppUserByUsername(user.username).isPresent())
            throw new Exception();
//        for (AppUser existingUser : users) {
//            if (AppUser.username.equals(existingUser.username)) {
//                throw new Exception();
//            }
//        }
        repository.save(user);
//        user.id = nextUserId++;
//        users.add(user);
    }
}
