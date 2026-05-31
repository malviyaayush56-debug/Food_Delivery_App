package com.FoodApp.FoodiesApi.service;

import com.FoodApp.FoodiesApi.model.User;
import com.FoodApp.FoodiesApi.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User registerUser(User user) {
        if(repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        return repository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        if (!user.getPassword().equals(password)) { // Note: Real project mein BCrypt password encoder lagana chahiye
            throw new RuntimeException("Invalid credentials!");
        }
        return user;
    }
}