package com.flashfocus.flashfocus.controller;

import com.flashfocus.flashfocus.model.User;
import com.flashfocus.flashfocus.dto.LoginRequest;
import com.flashfocus.flashfocus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        System.out.println("Incoming user: " + user);
        Optional<User> registeredUser = userService.register(user);
        if (registeredUser.isPresent()) {
            return ResponseEntity.ok(registeredUser.get());
        }
        else {
            return ResponseEntity.status(400).body(null); 
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        String emailOrUsername = loginRequest.getEmailOrUsername();
        String password = loginRequest.getPassword();
        System.out.println("Login attempt with email/username: " + emailOrUsername);

        Optional<User> loggedInUser = userService.login(emailOrUsername, password);
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser.get());
        } 
        else {
            return ResponseEntity.status(401).body(null);
        }
    }
}