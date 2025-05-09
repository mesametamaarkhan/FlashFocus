package com.flashfocus.flashfocus.repository;

import com.flashfocus.flashfocus.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findById(String id);
    Optional<User> findByEmailAndUsername(String email, String username);
    Optional<User> findByEmailOrUsername(String email, String username);
}