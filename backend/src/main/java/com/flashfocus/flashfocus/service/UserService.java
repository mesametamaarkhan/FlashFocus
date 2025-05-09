package com.flashfocus.flashfocus.service;

import com.flashfocus.flashfocus.model.User;
import com.flashfocus.flashfocus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> register(User user) {
        if(userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            return Optional.empty();
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setCreatedAt(Instant.now());
        user.setNotificationPreferences(new HashMap<>());
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> login(String emailOrUsername, String password) {
        Optional<User> user = userRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPasswordHash())) {
            return user;
        }
        return Optional.empty(); //user not found or password mismatch
    }
}
