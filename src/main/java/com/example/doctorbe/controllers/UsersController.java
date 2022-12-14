package com.example.doctorbe.controllers;

import com.example.doctorbe.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    private Long nextUserId = 0L;
    private ArrayList<User> users = new ArrayList<>();

//    FE: this.http.get<User[]>('http://localhost:3000/users')
//    HttpClient made an http request
//    BE: Tomcat received the request
//    Tomcat called the function below b/c it was mapped to /users, GET

    @GetMapping
    public Iterable<User> getByUsernameAndPassword(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        if (username == null && password == null)
            return users;

        if (password == null)
            for (User user : users) {
                if (user.username.equals(username)) {
                    return List.of(new User[]{user});
                }
            }
        else
            for (User user : users) {
                if (user.username.equals(username) && user.password.equals(password)) {
                    return List.of(new User[]{user});
                }
            }
        return List.of(new User[]{});
    }

    //    FE post request
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody User user) {
        for (User existingUser : users) {
            if (user.username.equals(existingUser.username)) {
                return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
            }
        }
        user.id = nextUserId++;
        users.add(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
