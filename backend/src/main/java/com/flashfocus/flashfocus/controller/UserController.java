package com.flashfocus.flashfocus.controller;

import com.flashfocus.flashfocus.model.User;
import com.flashfocus.flashfocus.dto.LoginRequest;
import com.flashfocus.flashfocus.dto.RegisterRequest;
import com.flashfocus.flashfocus.dto.UserDTO;
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
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();
        String confirmPassword = registerRequest.getConfirmPassword();

        if(password == null || !password.equals(confirmPassword)) {
            return ResponseEntity.status(400).body(null); // Bad request if passwords do not match
        }

        Optional<User> registeredUser = userService.register(username, email, password);
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

        Optional<User> loggedInUser = userService.login(emailOrUsername, password);
        if (loggedInUser.isPresent()) {
            return ResponseEntity.ok(loggedInUser.get());
        } 
        else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable String id) {
        Optional<UserDTO> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body(null); // Not found
        }
    }
}