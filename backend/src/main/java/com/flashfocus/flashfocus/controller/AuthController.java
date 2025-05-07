package com.flashfocus.controller;

import com.flashfocus.config.JwtUtil;
import com.flashfocus.model.User;
import com.flashfocus.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String jwt = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(userService.getUserProfile(email));
    }

    @PostMapping("/device-token")
    public ResponseEntity<?> updateDeviceToken(@RequestBody DeviceTokenRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User updatedUser = userService.updateDeviceToken(email, request.getDeviceToken());
        return ResponseEntity.ok(updatedUser);
    }

    static class DeviceTokenRequest {
        private String deviceToken;

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }
    }
}