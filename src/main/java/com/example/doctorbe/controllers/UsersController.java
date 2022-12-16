package com.example.doctorbe.controllers;

import com.example.doctorbe.models.AppUser;
import com.example.doctorbe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    private UserService service;
//    Dependency injection

    public UsersController(UserService service) {
        this.service = service;
    }

//    FE: this.http.get<User[]>('http://localhost:3000/users')
//    HttpClient made a http request
//    BE: Tomcat received the request
//    Tomcat called the function below b/c it was mapped to /users, GET

    @GetMapping
    public AppUser getByUsernameAndPassword(
            @RequestParam String username,
            @RequestParam String password) {
        final var user = service.getByUsernameAndPassword(username, password);

        if (user == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        return user;
    }

    //    FE post request
    @PostMapping
    public void register(@RequestBody AppUser user) {
        try {
            service.register(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }
}
