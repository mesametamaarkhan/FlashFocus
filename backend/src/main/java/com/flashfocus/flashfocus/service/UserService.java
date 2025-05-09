package com.flashfocus.flashfocus.service;

import com.flashfocus.flashfocus.model.User;
import com.flashfocus.flashfocus.repository.UserRepository;
import com.flashfocus.flashfocus.dto.RegisterRequest;
import com.flashfocus.flashfocus.dto.LoginRequest;
import com.flashfocus.flashfocus.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public Optional<User> register(String username, String email, String password) {
        if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username)) {
            return Optional.empty();
        }
    
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
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

    public Optional<UserDTO> getUserById(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        UserDTO dto = new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getNotificationPreferences()
        );
    
        return Optional.of(dto);
    }
    
    public Optional<UserDTO> updateUserProfile(String id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
    
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setNotificationPreferences(userDTO.getNotificationPreferences());
    
            User updatedUser = userRepository.save(user);
            UserDTO updatedDTO = new UserDTO(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getNotificationPreferences()
            );
    
            return Optional.of(updatedDTO);
        }
    
        return Optional.empty();
    }
    
}