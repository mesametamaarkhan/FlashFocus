package com.flashfocus.flashfocus.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}

// The RegisterRequest class is a Data Transfer Object (DTO) used to encapsulate the data required for user registration.
// It contains fields for the username, email, password, and a confirmation password.
// The @Data annotation from Lombok generates boilerplate code such as getters, setters, equals, hashCode, and toString methods.